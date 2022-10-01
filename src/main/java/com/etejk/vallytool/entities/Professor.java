package com.etejk.vallytool.entities;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Turma> turmas = new ArrayList<>();
	
	public Professor(Integer Id, String nome, String cpf, String senha, Character cargo) {
		super(Id, cargo, cpf, nome, senha);
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