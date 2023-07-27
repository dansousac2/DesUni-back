package com.wl.desafiounidac.model.entity;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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

	@Override
	public int hashCode() {
		return Objects.hash(date, id);
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
		return Objects.equals(date, other.date) && Objects.equals(id, other.id);
	}

}
