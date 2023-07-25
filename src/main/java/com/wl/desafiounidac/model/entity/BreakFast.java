package com.wl.desafiounidac.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BREAKFAST")
public class BreakFast {
	
	@Id
	@Column(name = "BREAKFAST_ID", nullable = false)
	private Integer id;
	
	@Column(name = "BREAKFAST_DATE", nullable = false)
	private LocalDate date;
	
	
	
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

}
