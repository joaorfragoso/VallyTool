
package com.etejk.vallytool.entities;

public enum Conceito {
	PESSIMO("Péssimo", "0"), INSUFICIENTE("Insuficiente", "1"), REGULAR("Regular", "2"), BOM("Bom", "3"),
	MUITOBOM("Muito Bom", "4");

	String conceito;
	String id;

	private Conceito(String conceito, String id) {
		this.conceito = conceito;
		this.id = id;
	}

	public static Conceito retornaConceito(String id) {
		switch(id) {
		case "0":
			return Conceito.PESSIMO;
		case "1":
			return Conceito.INSUFICIENTE;
		case "2":
			return Conceito.REGULAR;
		case "3":
			return Conceito.BOM;
		}
		
		return Conceito.MUITOBOM;
		}
		
	public String getConceito() {
		return conceito;
	}

	public void setConceito(String conceito) {
		this.conceito = conceito;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}