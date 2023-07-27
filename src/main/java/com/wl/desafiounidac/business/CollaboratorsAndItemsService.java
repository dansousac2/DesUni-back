package com.wl.desafiounidac.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl.desafiounidac.model.entity.CollaboratorsAndItems;
import com.wl.desafiounidac.model.repository.CollaboratorsAndItemsRepository;

@Service
public class CollaboratorsAndItemsService {

	@Autowired
	private CollaboratorsAndItemsRepository repository;
	
	public List<CollaboratorsAndItems> getCollabAndItemsOfBreakFast(Integer breakFastId){
		return repository.getCollabAndItemsOfBreakFast(breakFastId);
	}
	
}
