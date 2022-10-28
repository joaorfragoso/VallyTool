package com.etejk.vallytool.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etejk.vallytool.entities.Disciplina;
import com.etejk.vallytool.entities.Turma;
import com.etejk.vallytool.repositories.ProfessoRepository;
import com.etejk.vallytool.repositories.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	TurmaRepository tr;
	
	@Autowired
	ProfessoRepository pr;
	
	public List<Disciplina> disciplinasVinculadas(Integer id){
		List<Disciplina> discProf = pr.findById(id).get().getDisciplinas();
		List<Turma> turmaProf = pr.findById(id).get().getTurmas();
		
		int size = turmaProf.size();
		
		List<Disciplina> resultado = new ArrayList<>();
		for(int i = 0; i < size; i++) {
			Turma turma = turmaProf.get(i);
			List<Disciplina> discTurma = turma.getDisciplinas();
			for(int a = 0; a < discProf.size(); i++) {
				for(int b = 0; b < discTurma.size(); i++) {
					if(discProf.get(a).equals(discTurma.get(b))) {
						resultado.add(discProf.get(a));
					}
				}
			}
			
		}
		
		return resultado;
	}
}
