package com.etejk.vallytool.validators;

import java.time.Year;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etejk.vallytool.entities.Disciplina;
import com.etejk.vallytool.entities.Resultado;
import com.etejk.vallytool.entities.Trimestre;
import com.etejk.vallytool.entities.Turma;
import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.CompetenciaRepository;
import com.etejk.vallytool.repositories.DisciplinaRepository;
import com.etejk.vallytool.repositories.ProfessoRepository;
import com.etejk.vallytool.repositories.TurmaRepository;

@Service
public class ResultadoValidator {
	
	@Autowired
	private TurmaRepository tr;
	
	@Autowired
	private ProfessoRepository ur;
	
	@Autowired
	private DisciplinaRepository dr;
	
	public Turma retornarTurma(String turma) {
		return tr.findByCodigo(turma);
	}
	
	public Disciplina retornarDisciplina(String disciplina) {
		return dr.findByNome(disciplina);
	}
	
	public Usuario retornarUsuario(String usuario) {
		return ur.findByNome(usuario);
	}
}
