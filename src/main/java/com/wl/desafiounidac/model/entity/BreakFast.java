package com.wl.desafiounidac.model.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "BREAKFAST")
public class BreakFast {
	
	@Id
	@Column(name = "BREAKFAST_ID", nullable = false)
	private Integer id;
	
	@Column(name = "BREAKFAST_DATE", nullable = false)
	private LocalDate date;
	
	@ManyToMany()
	@JoinTable(
			name = "BREAKFAST_ITENS",
			joinColumns = @JoinColumn(name = "BREAKFAST_ID"),
			inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
	)
	private List<Item> itens;
	
	@ManyToMany
	@JoinTable(
			name = "BREAKFAST_COLLABORATOR",
			joinColumns = @JoinColumn(name = "BREAKFAST_ID"),
			inverseJoinColumns = @JoinColumn(name = "COLLABORATOR_ID")
			)
	private List<Collaborator> collaborators;
	
	public BreakFast() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(collaborators, date, id, itens);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BreakFast other = (BreakFast) obj;
		return Objects.equals(collaborators, other.collaborators) && Objects.equals(date, other.date)
				&& Objects.equals(id, other.id) && Objects.equals(itens, other.itens);
	}

}
