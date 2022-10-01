package com.etejk.vallytool.entities;

import java.util.ArrayList;
import java.util.List;

public class Turma {
	private String codigo;
	
	private List<Disciplina> disciplinas = new ArrayList<>();
	
	public Turma() { }
	public Turma(String codigo) {
		this.codigo = codigo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void addDisciplina(Disciplina disciplina) {
		disciplinas.add(disciplina);
	}
	
	
}