package com.etejk.vallytool.validators;

import java.time.Year;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.etejk.vallytool.entities.Disciplina;
import com.etejk.vallytool.entities.Resultado;
import com.etejk.vallytool.entities.Trimestre;
import com.etejk.vallytool.entities.Turma;
import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.CompetenciaRepository;
import com.etejk.vallytool.repositories.DisciplinaRepository;
import com.etejk.vallytool.repositories.ProfessoRepository;
import com.etejk.vallytool.repositories.TurmaRepository;

public class ResultadoValidator {
	
	@Autowired
	TurmaRepository tr;
	@Autowired
	DisciplinaRepository dr;
	@Autowired
	ProfessoRepository ur;
	@Autowired
	CompetenciaRepository cr;
	
	public boolean verificar(String turma,
			String disciplina,
			String usuario,
			Map<String, String> competenciaNota,
			String trimestre,
			String ano) {
		
		if(tr.findByCodigo(turma) == null) {
			throw new IllegalStateException("turma não existe no banco de dados");
		}
		if(dr.findByNome(disciplina) == null) {
			throw new IllegalStateException("disciplina não existe no banco de dados");
		}
		if(ur.findByNome(usuario) == null) {
			throw new IllegalStateException("turma já existe no banco de dados");
		}
	
		return true;
	}
}
