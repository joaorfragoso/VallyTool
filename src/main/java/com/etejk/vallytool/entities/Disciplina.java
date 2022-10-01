package com.etejk.vallytool.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

public class Disciplina {
	private String nome;
	
	@OneToMany()
	private List<Competencia> competencias = new ArrayList<>(); 
	
	public Disciplina() {
	}
	
	public Disciplina(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Competencia> getCompetencias() {
		return competencias;
	}
	
	public void addCompetencia(Competencia competencia) {
		competencias.add(competencia);
	}
	
	
}