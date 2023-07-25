package com.wl.desafiounidac.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl.desafiounidac.model.entity.Collaborator;
import com.wl.desafiounidac.model.repository.CollaboratorRepository;
import com.wl.desafiounidac.presentation.dtos.CollaboratorDto;
import com.wl.desafiounidac.presentation.dtos.CollaboratorDtoCpf;
import com.wl.desafiounidac.presentation.exceptions.CollaboratorNotFoundException;
import com.wl.desafiounidac.presentation.exceptions.CpfAlreadyExists;
import com.wl.desafiounidac.presentation.exceptions.CpfNotFindedException;

import jakarta.validation.Valid;

@Service
public class CollaboratorService {

	@Autowired
	private CollaboratorRepository repository;
	
	public List<Collaborator> getAllCollaborators(){
		return repository.getAllCollaborators();
	}
	
	public Collaborator getCollaboratorByCpf(@Valid String cpf) throws CollaboratorNotFoundException {
		
		Collaborator collaborator = repository.getCollaboratorByCpf(cpf);
		
		if(collaborator == null) {
			throw new CollaboratorNotFoundException("Colaborador(a) não encontrado(a)");
		}
		
		return collaborator;
	}
	
	public boolean existsCollaboratorByCpf(String cpf) {
		if(cpf == null || cpf.isBlank()) {
			throw new IllegalArgumentException("CPF não pode ser nulo");
		}
		return repository.existsCollaboratorByCpf(cpf) == 1;
	}
	
	public void createCollaborator(@Valid CollaboratorDto dto) throws CpfAlreadyExists {
		if(existsCollaboratorByCpf(dto.getCpf())) {
			throw new CpfAlreadyExists("CPF já encontra-se cadastrado");
		}
		repository.createCollaborator(dto.getCpf(), dto.getName());
	}
	
	public void updateCollaboratorName(@Valid CollaboratorDto dto) throws CpfNotFindedException {
		if(!existsCollaboratorByCpf(dto.getCpf())) {
			throw new CpfNotFindedException("O CPF informado não foi encontrado");
		}
		
		repository.updateCollaboratorName(dto.getCpf(), dto.getName());
	}
	
	public void removeCollaborator(@Valid CollaboratorDtoCpf dto) throws CpfNotFindedException {
		if(!existsCollaboratorByCpf(dto.getCpf())) {
			throw new CpfNotFindedException("O CPF informado não foi encontrado");
		}
		
		repository.removeCollaborator(dto.getCpf());
	}
}
