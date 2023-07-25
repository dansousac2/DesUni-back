package com.wl.desafiounidac.model.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "COLLABORATOR")
public class Collaborator {
	
	@Id
	@Column(name = "COLLABORATOR_CPF", nullable = false)
	private String cpf;
	
	@Column(name = "COLLABORATOR_NAME", nullable = false)
	private String name;
	
	public Collaborator() {
	};
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cpf, name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Collaborator other = (Collaborator) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(name, other.name);
	}

}
