package com.wl.desafiounidac.presentation.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CollaboratorDto {
	
	@NotBlank(message = "Nome não pode ser nulo")
	private String name;
	
	@Pattern(regexp = "^[0-9]{11}$", message = "CPF não contém 11 dígitos")
	private String cpf;
	
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
	
}
