package com.etejk.vallytool.dao;

import java.time.Year;


import java.util.ArrayList;
import java.util.List;


import com.etejk.vallytool.entities.Conceito;

public class ResultadoDAO {
	private String turma;
	private String disciplina;
	private String atitude;
	private String participacao;
	private String aproveitamento;
	private String entrosamento;
	private String frequencia;
	private String relacao;
	private List<String> conceitos = new ArrayList<>();
	private String usuario;
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
	
	public List<String> addConceitos(){
		conceitos.add(Conceito.retornaConceito(atitude).getConceito());
		conceitos.add(Conceito.retornaConceito(participacao).getConceito());
		conceitos.add(Conceito.retornaConceito(aproveitamento).getConceito());
		conceitos.add(Conceito.retornaConceito(entrosamento).getConceito());
		conceitos.add(Conceito.retornaConceito(frequencia).getConceito());
		conceitos.add(Conceito.retornaConceito(relacao).getConceito());
		
		return conceitos;
		
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

	@Override
	public String toString() {
		return "ResultadoDAO [turma=" + turma + ", disciplina=" + disciplina + ", atitude=" + atitude
				+ ", participacao=" + participacao + ", aproveitamento=" + aproveitamento + ", entrosamento="
				+ entrosamento + ", frequencia=" + frequencia + ", relacao=" + relacao + ", conceitos=" + conceitos
				+ ", usuario=" + usuario + ", trimestre=" + trimestre + ", ano=" + ano + "]";
	}

	public String getAtitude() {
		return atitude;
	}

	public void setAtitude(String atitude) {
		this.atitude = atitude;
	}

	public String getParticipacao() {
		return participacao;
	}

	public void setParticipacao(String participacao) {
		this.participacao = participacao;
	}

	public String getAproveitamento() {
		return aproveitamento;
	}

	public void setAproveitamento(String aproveitamento) {
		this.aproveitamento = aproveitamento;
	}

	public String getEntrosamento() {
		return entrosamento;
	}

	public void setEntrosamento(String entrosamento) {
		this.entrosamento = entrosamento;
	}

	public String getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}

	public String getRelacao() {
		return relacao;
	}

	public void setRelacao(String relacao) {
		this.relacao = relacao;
	}

	public List<String> getConceitos() {
		return conceitos;
	}

	public void setConceitos(List<String> conceitos) {
		this.conceitos = conceitos;
	}
}
