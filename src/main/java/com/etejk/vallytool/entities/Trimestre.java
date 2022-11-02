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
	
	public static Trimestre getTrimestre(Integer id) {
		if(id == 1) {
			return Trimestre.PRIMEIRO;
		}else if (id == 2) {
			return Trimestre.SEGUNDO;
		}

		return Trimestre.TERCEIRO;
	}
	
}
