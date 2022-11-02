package com.etejk.vallytool.entities;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "trimestre_atual")
public class TrimestreDatabase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Enumerated(EnumType.STRING)
	public Trimestre trimestre;
	public boolean aberto;
	
	public TrimestreDatabase() {
		
	}

	public TrimestreDatabase(Trimestre trimestre, boolean aberto) {
		super();
		this.trimestre = trimestre;
		this.aberto = aberto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Trimestre getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Trimestre trimestre) {
		this.trimestre = trimestre;
	}

	public boolean isAberto() {
		return aberto;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}
	
	
}
