package com.etejk.vallytool.entities;

public enum Trimestre {
	
	PRIMEIRO(1),SEGUNDO(2),TERCEIRO(3);
	
	public Integer id;
	
	private Trimestre(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
