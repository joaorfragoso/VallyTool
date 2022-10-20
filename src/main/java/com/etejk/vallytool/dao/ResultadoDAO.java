package com.etejk.vallytool.dao;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;

import com.etejk.vallytool.entities.Competencia;
import com.etejk.vallytool.entities.Disciplina;
import com.etejk.vallytool.entities.Conceito;
import com.etejk.vallytool.entities.Resultado;
import com.etejk.vallytool.entities.Trimestre;
import com.etejk.vallytool.entities.Turma;
import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.TurmaRepository;

public class ResultadoDAO {
	private String turma;
	private String disciplina;
	private String usuario;
	private Map<String, String> competenciaNota = new HashMap<>(); 
	private String trimestre;
	private String ano;
	
	public ResultadoDAO() {
		
	}

	public ResultadoDAO(String turma, String disciplina, String usuario, String trimestre, String ano) {
		super();
		this.turma = turma;
		this.disciplina = disciplina;
		this.usuario = usuario;
		this.trimestre = trimestre;
		this.ano = ano;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(String trimestre) {
		this.trimestre = trimestre;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Resultado toResultado() {

		return null;
	}
}
