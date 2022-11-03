package com.etejk.vallytool.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "turmas")
public class Turma {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	@NotBlank
	private String codigo;
	private boolean ativada;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Disciplina> disciplinas;

	public Turma() { }
	
	public Turma(String codigo, List<Disciplina> disciplinas) {
		this.codigo = codigo;
		this.disciplinas = disciplinas;
		this.ativada = true;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public boolean isAtivada() {
		return ativada;
	}
	public void setAtivada(boolean ativada) {
		this.ativada = ativada;
	}
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	public void addDisciplina(Disciplina disciplina) {
		this.disciplinas.add(disciplina);
	}
	
	
}