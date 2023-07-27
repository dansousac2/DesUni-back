package com.wl.desafiounidac.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wl.desafiounidac.business.BreakFastService;
import com.wl.desafiounidac.business.CollaboratorService;
import com.wl.desafiounidac.business.CollaboratorsAndItemsService;
import com.wl.desafiounidac.business.ItemService;
import com.wl.desafiounidac.model.entity.BreakFast;
import com.wl.desafiounidac.model.entity.CollaboratorsAndItems;
import com.wl.desafiounidac.presentation.dtos.BreakFastCollabsAndItemsDto;
import com.wl.desafiounidac.presentation.dtos.BreakFastDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/breakfast")
public class BreakFastController {

	@Autowired
	private BreakFastService service;
	
	@Autowired
	private CollaboratorService collabService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CollaboratorsAndItemsService collabsAndItemsService;
	
	@GetMapping("/all")
	public ResponseEntity getAllBreakFasts() {
		try {
			List<BreakFast> allBreakFasts = service.getAllBreakFasts();
			
			return ResponseEntity.status(HttpStatus.OK).body(allBreakFasts);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity getCurrentBreakFast() {
		try {
			BreakFast breakFast = service.getCurrentBreakFast();
			
			List<CollaboratorsAndItems> collabsAndItems = service.getCollabAndItemsOfBreakFast(breakFast.getId(), collabsAndItemsService);
			
			BreakFastCollabsAndItemsDto dto = new BreakFastCollabsAndItemsDto(breakFast, collabsAndItems);
			
			return ResponseEntity.ok(dto);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity createBreakFast(@RequestBody @Valid BreakFastDto dto) {
		try {
			service.createBreakFast(dto.getDate(), dto.getItemsId(), dto.getCollaboratorCpf(), itemService, collabService);
			
			return ResponseEntity.status(HttpStatus.CREATED).build();
					
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping
	public ResponseEntity insertCollaboratorAndItems(@RequestBody @Valid BreakFastDto dto) {
		try {
			service.insertCollaboratorAndItems(dto, collabService, itemService);
			
			return ResponseEntity.ok().build();
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/confirmeitemcolaboration")
	public ResponseEntity confirmeItemCollaboration(@RequestBody BreakFastCollabsAndItemsDto dto) {
		try {
			service.confirmeItemCollaboration(dto, collabService, itemService);
			
			return ResponseEntity.ok().build();
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
