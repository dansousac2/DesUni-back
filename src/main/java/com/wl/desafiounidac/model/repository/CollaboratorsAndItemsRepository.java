package com.wl.desafiounidac.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.wl.desafiounidac.model.entity.Collaborator;
import com.wl.desafiounidac.model.entity.CollaboratorsAndItems;

import jakarta.transaction.Transactional;

public interface CollaboratorsAndItemsRepository extends JpaRepository<CollaboratorsAndItems, Integer>{

	@Query(nativeQuery = true, value = "SELECT * FROM collaborators_and_items WHERE breakfast_id = :breakFastId")
	List<CollaboratorsAndItems> getCollabAndItemsOfBreakFast(Integer breakFastId);
}
