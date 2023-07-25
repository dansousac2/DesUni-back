package com.wl.desafiounidac.presentation.exceptions;

public class CpfAlreadyExists extends Exception {
	private static final long serialVersionUID = 1L;

	public CpfAlreadyExists(String msg) {
		super(msg);
	}
}
