package com.etejk.vallytool.entities;

public class Competencia {
	private String nome;
	private Nota nota;
	
	public Competencia() {}
	public Competencia(String nome, Nota nota) {
		this.nome = nome;
		this.nota = nota;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Nota getNota() {
		return nota;
	}
	public void setNota(Nota nota) {
		this.nota = nota;
	}
	
}
