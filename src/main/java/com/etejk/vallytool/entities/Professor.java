package com.etejk.vallytool.entities;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario {
	private List<Turma> turmas = new ArrayList<>();
	
	public Professor(String nome, String cpf, String senha, Character cargo) {
		super(nome, cpf, senha, cargo);
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void addTurmas(Turma turma) {
		turmas.add(turma);
	}
	
	public void avaliarTurma(Turma turma) {
	
	}
	
}
