package com.wl.desafiounidac.presentation.dtos;

import java.util.List;

import com.wl.desafiounidac.model.entity.BreakFast;
import com.wl.desafiounidac.model.entity.CollaboratorsAndItems;

public class BreakFastCollabsAndItemsDto {

	private BreakFast breakFast;
	private List<CollaboratorsAndItems> collabsAndItems;
	
	public BreakFastCollabsAndItemsDto(BreakFast breakFast, List<CollaboratorsAndItems> collabsAndItems) {
		this.breakFast = breakFast;
		this.collabsAndItems = collabsAndItems;
	}

	public BreakFast getBreakFast() {
		return breakFast;
	}

	public void setBreakFast(BreakFast breakFast) {
		this.breakFast = breakFast;
	}

	public List<CollaboratorsAndItems> getCollabsAndItems() {
		return collabsAndItems;
	}

	public void setCollabsAndItems(List<CollaboratorsAndItems> collabsAndItems) {
		this.collabsAndItems = collabsAndItems;
	}

}
