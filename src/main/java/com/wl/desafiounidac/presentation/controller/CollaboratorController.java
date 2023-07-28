package com.wl.desafiounidac.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wl.desafiounidac.business.CollaboratorService;
import com.wl.desafiounidac.model.entity.Collaborator;
import com.wl.desafiounidac.presentation.dtos.CollaboratorDto;
import com.wl.desafiounidac.presentation.dtos.CollaboratorDtoCpf;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/collaborator")
public class CollaboratorController {

	@Autowired
	private CollaboratorService service;
	
	@GetMapping("/all")
	public ResponseEntity getAllCollaborators() {
		try {
			List<Collaborator> allCollaborators = service.getAllCollaborators();
			
			return ResponseEntity.status(HttpStatus.OK).body(allCollaborators);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/usingcpf")
	public ResponseEntity getCollaboratorByCpf(@RequestBody @Valid CollaboratorDtoCpf dto) {
		try {
			Collaborator collaborator = service.getCollaboratorByCpf(dto.getCpf());
			
			return ResponseEntity.ok(collaborator);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity createNewCollaborator(@RequestBody @Valid CollaboratorDto dto) {
		try {
			service.createCollaborator(dto);
			
			return ResponseEntity.status(HttpStatus.CREATED).build();
					
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping
	public ResponseEntity updateCollaboratorName(@RequestBody @Valid CollaboratorDto dto) {
		try {
			service.updateCollaboratorName(dto);
			
			return ResponseEntity.status(HttpStatus.OK).build();
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/removeCollab")
	public ResponseEntity removeCollaborator(@RequestBody @Valid CollaboratorDtoCpf dto) {
		try {
			service.removeCollaborator(dto);
			
			return ResponseEntity.status(HttpStatus.OK).build();
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
