package com.etejk.vallytool.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "resultado")
public class Resultado implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	@OneToOne
	private Turma turma;
	@OneToOne
	private Disciplina disciplina;
	@OneToOne
	private Usuario usuario;
	@ElementCollection
	private List<String> conceitos;
	@Enumerated(EnumType.STRING)
	private Trimestre trimestre;
	private LocalDate data;
	
	public Resultado() {
		
	}

	public Resultado(Turma turma, Disciplina disciplina, List<String> conceitos, Usuario usuario, Trimestre trimestre) {
		super();
		this.turma = turma;
		this.disciplina = disciplina;
		this.conceitos = conceitos;
		this.usuario = usuario;
		this.trimestre = trimestre;
		this.data = LocalDate.now();
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

	public Trimestre getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Trimestre trimestre) {
		this.trimestre = trimestre;
	}

	public List<String> getConceitos() {
		return conceitos;
	}

	public void setConceitos(List<String> conceitos) {
		this.conceitos = conceitos;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}


	
	
}
