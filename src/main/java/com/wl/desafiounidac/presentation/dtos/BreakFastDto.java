package com.wl.desafiounidac.presentation.dtos;

import java.time.LocalDate;
import java.util.Set;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class BreakFastDto {
	
	@Future(message = "A data do café deve ser posterior ao dia atual")
	private LocalDate date;
	
	@Pattern(regexp = "^[0-9]{11}$", message = "O cpf do colaborador deve conter apenas números")
	private String collaboratorCpf;
	
	@NotEmpty(message = "Set de IDs de itens não deve estar vazio")
	private Set<Integer> itemsId;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getCollaboratorCpf() {
		return collaboratorCpf;
	}

	public void setCollaboratorCpf(String collaboratorCpf) {
		this.collaboratorCpf = collaboratorCpf;
	}

	public Set<Integer> getItemsId() {
		return itemsId;
	}

	public void setItemsId(Set<Integer> itemsId) {
		this.itemsId = itemsId;
	}

}
