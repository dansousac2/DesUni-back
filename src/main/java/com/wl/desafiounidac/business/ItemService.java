package com.wl.desafiounidac.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl.desafiounidac.model.entity.Item;
import com.wl.desafiounidac.model.enums.SeqSql;
import com.wl.desafiounidac.model.repository.ItemRepository;
import com.wl.desafiounidac.presentation.exceptions.CollaboratorNotFoundException;
import com.wl.desafiounidac.presentation.exceptions.ItemAlreadyExists;
import com.wl.desafiounidac.presentation.exceptions.ItemNotFindedException;

import jakarta.validation.Valid;

@Service
public class ItemService {

	@Autowired
	private ItemRepository repository;
	
	public List<Item> getAllItens(){
		return repository.getAllItens();
	}
	
	public Item getItemById(@Valid Integer id) throws CollaboratorNotFoundException, ItemNotFindedException {
		if(id == null || id < 1) {
			throw new IllegalArgumentException("Id de item inválido");
		}
		
		Item item = repository.getItemById(id);
		
		if(item == null) {
			throw new ItemNotFindedException("Colaborador(a) não encontrado(a)");
		}
		
		return item;
	}
	
	public boolean existsItemByName(String name) {
		if(name == null || name.isBlank()) {
			throw new IllegalArgumentException("O nome do Item não deve ser nulo");
		}
		return repository.existsItemByName(name) == 1;
	}
	
	public boolean existsSequence() {
		return repository.existsSequenceByName(SeqSql.SEQ_ITEM.toString().toLowerCase()) == 1;
	}
	
	public void createItem(String name) throws ItemAlreadyExists {
		if(name == null || name.isBlank()) {
			throw new IllegalArgumentException("Nome de item inválido");
		}
		if(existsItemByName(name)) {
			throw new ItemAlreadyExists("Item já encontra-se cadastrado");
		}
		if(!existsSequence()) {
			repository.createSequence();
		}
		
		repository.createItem(name, SeqSql.SEQ_ITEM.toString());
	}
	
	public void removeItem(String name) throws ItemNotFindedException {
		if(name == null || name.isBlank()) {
			throw new IllegalArgumentException("Nome do item não pode ser nulo");
		}
		if(!existsItemByName(name)) {
			throw new ItemNotFindedException("O Item informado não foi encontrado");
		}
		
		repository.removeItem(name);
	}
}
