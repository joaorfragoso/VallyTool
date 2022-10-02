package com.etejk.vallytool.entities;

import java.time.Year;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "resultado")
public class Resultado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer Id;
	@ManyToOne
	@JoinColumn(name = "idturma")
	public Turma turma;
	@ManyToOne
	@JoinColumn(name = "iddisciplina")
	public Disciplina disciplina;
	@ManyToOne
	@JoinColumn(name = "idusuario")
	public Usuario usuario;
	@ManyToOne
	@JoinColumn(name = "idcompetencia")
	public Competencia competencia;
	@Enumerated(EnumType.STRING)
	public Nota nota;
	@Enumerated(EnumType.STRING)
	public Trimestre trimestre;
	public Year ano;
	
	public Resultado() {
		
	}

	public Resultado(Integer id, Turma turma, Disciplina disciplina, Usuario usuario, Competencia competencia,
			Nota nota, Trimestre trimestre, Year ano) {
		super();
		Id = id;
		this.turma = turma;
		this.disciplina = disciplina;
		this.usuario = usuario;
		this.competencia = competencia;
		this.nota = nota;
		this.trimestre = trimestre;
		this.ano = ano;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public Trimestre getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Trimestre trimestre) {
		this.trimestre = trimestre;
	}

	public Year getAno() {
		return ano;
	}

	public void setAno(Year ano) {
		this.ano = ano;
	}
	
	
}
