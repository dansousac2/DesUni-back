package com.wl.desafiounidac.model.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "COLLABORATORS_AND_ITEMS")
public class CollaboratorsAndItems {

	@Id
	@Column(name = "COLLAB_AND_ITEM_ID", nullable = false)
	private Integer id;
	
	@Column(name = "BREAKFAST_ID", nullable = false)
	private Integer breakFastId;
	
	@Column(name = "COLLABORATOR_ID", nullable = false)
	private String collaboratorId;
	
	@Column(name = "ITEM_ID", nullable = false)
	private Integer itemId;
	
	@Column(name = "BROUGHT_ITEM_TO_BREAKFAST", nullable = false)
	private boolean broughtItem;
	
	public CollaboratorsAndItems() {
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBreakFastId() {
		return breakFastId;
	}
	public void setBreakFastId(Integer breakFastId) {
		this.breakFastId = breakFastId;
	}
	public String getCollaboratorId() {
		return collaboratorId;
	}
	public void setCollaboratorId(String collaboratorId) {
		this.collaboratorId = collaboratorId;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public boolean isBroughtItem() {
		return broughtItem;
	}

	public void setBroughtItem(boolean broughtItem) {
		this.broughtItem = broughtItem;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemId, breakFastId, broughtItem, collaboratorId, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CollaboratorsAndItems other = (CollaboratorsAndItems) obj;
		return Objects.equals(itemId, other.itemId) && Objects.equals(breakFastId, other.breakFastId)
				&& broughtItem == other.broughtItem && Objects.equals(collaboratorId, other.collaboratorId)
				&& Objects.equals(id, other.id);
	}
	
}
