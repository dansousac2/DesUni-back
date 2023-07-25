package com.wl.desafiounidac.presentation.dtos;

import jakarta.validation.constraints.Pattern;

public class CollaboratorDtoCpf {

	@Pattern(regexp = "^[0-9]{11}$", message = "CPF não contém 11 dígitos")
	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
