
package com.etejk.vallytool.entities;

public enum Conceito {
	PESSIMO("Péssimo"),
	INSUFICIENTE("Insuficiente"),
	REGULAR("Regular"),
	BOM("Bom"),
	MUITOBOM("Muito Bom");
	
	String conceito;
	private Conceito(String conceito) {
		this.conceito = conceito;
	}
	
	public String getNota() {
		return conceito;
	}
}