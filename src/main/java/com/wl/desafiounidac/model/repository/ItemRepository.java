package com.wl.desafiounidac.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.wl.desafiounidac.model.entity.Item;

import jakarta.transaction.Transactional;

public interface ItemRepository extends JpaRepository<Item, Integer>{

	@Query(nativeQuery = true, value = "SELECT * FROM item")
	List<Item> getAllItens();
	
	@Query(nativeQuery = true, value = "SELECT * FROM item WHERE id_item = :id")
	Item getItemById(Integer id);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM item WHERE name_item = :name")
	int existsItemByName(String name);
	
	@Query(nativeQuery = true, value = "SELECT count(*) FROM information_schema.sequences "
			+ "WHERE sequence_name = :name")
	int existsSequenceByName(String name);
	
	@Query(nativeQuery = true, value = "CREATE SEQUENCE :name START WITH 1")
	void createSequence(String name);
	
	// neste ponto uma sequence no DB deve ter sido criada posteriormente ou já existir
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO item VALUES(nextval(:sequenceName), :name)")
	void createItem(String name, String sequenceName);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM item WHERE name_item = :name")
	void removeItem(String name);
}
