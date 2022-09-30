package com.etejk.vallytool.entities;

import java.util.ArrayList;

import java.util.List;

public class SOP extends Usuario {
	private List<Professor> professores = new ArrayList<>();
	private List<Turma> turmas = new ArrayList<>();

	public SOP(Integer id, String nome, String cpf, String senha, Character cargo) {
		super(id, cargo, cpf, nome, senha);
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void addProfessores(Professor professor) {
		professores.add(professor);
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void addTurmas(Turma turma) {
		turmas.add(turma);
	}

	public void gerenciarTurma(Turma turma, Professor professor, Disciplina disciplina) {

	}

	public void cadastrarUsuario(String cpf, String nome, String senha, String tipoUsuario) {

	}

	public void alterarUsuario(String cpf) {

	}

	public void excluirUsuario(String cpf) {

	}

	public void cadastrarTurma(String codigo, List<Disciplina> disciplinas) {

	}

	public void alterarTurma(String codigo, List<Disciplina> disciplinas) {

	}
	
	public void excluirTurma(String codigo) {
		
	}
	
	public void emitirResultado() {
		
	}
}
