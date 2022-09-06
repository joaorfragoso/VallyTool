package com.etejk.vallytool.entities;

public enum Nota {
	PESSIMO("Péssimo"),
	INSUFICIENTE("Insuficiente"),
	REGULAR("Regular"),
	BOM("Bom"),
	MUITOBOM("Muito Bom");
	
	String nota;
	private Nota(String nota) {
		this.nota = nota;
	}
	
	public String getNota() {
		return nota;
	}
}
