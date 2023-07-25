package com.wl.desafiounidac.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.wl.desafiounidac.model.entity.Collaborator;

import jakarta.transaction.Transactional;

public interface CollaboratorRepository extends JpaRepository<Collaborator, String>{

	@Query(nativeQuery = true, value = "SELECT * FROM collaborator")
	List<Collaborator> getAllCollaborators();
	
	@Query(nativeQuery = true, value = "SELECT * FROM collaborator WHERE collaborator_cpf = :cpf")
	Collaborator getCollaboratorByCpf(String cpf);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO collaborator VALUES(:cpf, :name)")
	void createCollaborator(String cpf, String name);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE collaborator SET collaborator_name = :name WHERE collaborator_cpf = :cpf")
	void updateCollaboratorName(String cpf, String name);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM collaborator WHERE collaborator_cpf = :cpf")
	int existsCollaboratorByCpf(String cpf);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM collaborator WHERE collaborator_cpf = :cpf")
	void removeCollaborator(String cpf);
}
