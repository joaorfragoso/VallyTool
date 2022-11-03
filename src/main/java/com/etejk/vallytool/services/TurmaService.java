package com.etejk.vallytool.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etejk.vallytool.entities.Disciplina;
import com.etejk.vallytool.entities.Relacao;
import com.etejk.vallytool.entities.Turma;
import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.ProfessoRepository;
import com.etejk.vallytool.repositories.RelacaoRepository;
import com.etejk.vallytool.repositories.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	TurmaRepository tr;
	
	@Autowired
	ProfessoRepository pr;
	
	@Autowired
	RelacaoRepository rr;
	
	public List<Disciplina> disciplinasVinculadas(Integer id){
		Usuario usuario = pr.findById(id).get();
		List<Relacao> relacoes = rr.findByUsuario(usuario);
		
		List<Disciplina> disciplinas = new ArrayList<>();
		for (Relacao relacao : relacoes) {
			Disciplina disciplina = relacao.getDisciplina();
			if(!disciplinas.contains(disciplina)) {
				disciplinas.add(disciplina);
			}
		}
		
		return disciplinas;
	}
}
