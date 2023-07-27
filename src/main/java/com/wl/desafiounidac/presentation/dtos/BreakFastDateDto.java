package com.wl.desafiounidac.presentation.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;

public class BreakFastDateDto {
	
	@Future(message = "A data do café deve ser posterior ao dia atual")
	private LocalDate date;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
