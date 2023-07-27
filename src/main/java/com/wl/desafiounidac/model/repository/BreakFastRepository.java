package com.wl.desafiounidac.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.wl.desafiounidac.model.entity.BreakFast;

import jakarta.transaction.Transactional;

public interface BreakFastRepository extends JpaRepository<BreakFast, String>{

	@Query(nativeQuery = true, value = "SELECT * FROM breakfast")
	List<BreakFast> getAllBreakFasts();
	
	@Query(nativeQuery = true, value = "SELECT * FROM breakfast WHERE breakfast_date = :date")
	BreakFast getBreakFast(LocalDate date);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM breakfast WHERE breakfast_date = :date")
	int existsBreakFast(LocalDate date);
	
	@Query(nativeQuery = true, value = "SELECT count(*) FROM information_schema.sequences "
			+ "WHERE sequence_name = :name AND sequence_catalog = 'CoffeeMorning'")
	int existsSequence(String name);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "CREATE SEQUENCE seq_break_fast START WITH 1")
	void createSequenceBreakFast();
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "CREATE SEQUENCE seq_collab_and_item START WITH 1")
	void createSequenceCollAndItem();

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO breakfast VALUES(nextval(:breakFastSeqName), :date)")
	void createBreakFast(String breakFastSeqName, LocalDate date);
	
	@Query(nativeQuery = true, value = "SELECT CASE WHEN is_called = false "
			+ "THEN last_value "
			+ "ELSE last_value + 1 "
			+ "END "
			+ "FROM seq_break_fast")
	int verifyNextBreakFastId();

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO collaborators_and_items VALUES(nextval(:sequenceCollabAndItems), "
			+ ":itemId, :breakFastId, false, :collaboratorCpf)")
	void insertIntoCollabAndItems(String sequenceCollabAndItems, Integer itemId, Integer breakFastId, String collaboratorCpf);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM collaborators_and_items WHERE collaborator_id = :collaboratorCpf "
			+ "AND breakfast_id = :breakFastId")
	int isCollaboratorInCollabAndItemTable(String collaboratorCpf, Integer breakFastId);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM collaborators_and_items WHERE item_id = :itemId "
			+ "AND breakfast_id = :breakFastId")
	int isItemInCollabAndItemTable(Integer itemId, Integer breakFastId);

	//TODO
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE collaborators_and_items SET brought_item_to_breakfast = :broughtItem "
			+ "WHERE breakfast_id = :breakFastId AND collaborator_id = :collaboratorId AND item_id = :itemId")
	void confirmeItemCollaboration(Integer breakFastId, String collaboratorId, Integer itemId, boolean broughtItem);

}
