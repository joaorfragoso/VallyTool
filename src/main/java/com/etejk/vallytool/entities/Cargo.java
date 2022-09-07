package com.etejk.vallytool.entities;

public enum Cargo {
	SOP('S'),
	PROFESSOR('P');
	
	private Character cargo;
	
	private Cargo(Character cargo) {
		this.cargo = cargo;
	}
	
	public Character getCargo() {
		return cargo;
	}
}
