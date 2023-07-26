package com.wl.desafiounidac.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wl.desafiounidac.business.ItemService;
import com.wl.desafiounidac.model.entity.Item;

@RestController
@RequestMapping("api/item")
public class ItemController {

	@Autowired
	private ItemService service;
	
	@GetMapping("/all")
	public ResponseEntity getAllItens() {
		try {
			List<Item> allItens = service.getAllItens();
			
			return ResponseEntity.status(HttpStatus.OK).body(allItens);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getItemById(@PathVariable Integer id) {
		try {
			Item item = service.getItemById(id);
			
			return ResponseEntity.ok(item);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/{name}")
	public ResponseEntity createNewItem(@PathVariable String name) {
		try {
			service.createItem(name);
			
			return ResponseEntity.status(HttpStatus.CREATED).build();
					
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity removeCollaborator(@PathVariable String name) {
		try {
			service.removeItem(name);
			
			return ResponseEntity.status(HttpStatus.OK).build();
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
