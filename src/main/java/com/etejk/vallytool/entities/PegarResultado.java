package com.etejk.vallytool.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.etejk.vallytool.repositories.RelacaoRepository;
import com.etejk.vallytool.repositories.ResultadoRepository;
import com.etejk.vallytool.repositories.TurmaRepository;
import com.etejk.vallytool.repositories.UsuarioRepository;

public class PegarResultado {
	
	ResultadoRepository rr;
	
	RelacaoRepository rer;

	public PegarResultado(ResultadoRepository rr,
			RelacaoRepository rer) {
		this.rr = rr;
		this.rer = rer;
	}
	
	public List<Resultado> pegarResultado(Integer ano, Turma turma, String trimestre) {
		return rr.findByEverything(ano, Trimestre.valueOf(trimestre), turma);
	}
	
	public String verificarResultados(Integer ano, Turma turma, String trimestre, Usuario usuario) {
		List<Resultado> resultados = rr.findByEverything(ano, Trimestre.valueOf(trimestre), turma);
		if(resultados.isEmpty()) {
			return "Não avaliada";
		}
		
		List<Relacao> relacoes = rer.findByTurmaAndUsuario(turma, usuario);
		List<Disciplina> disciplinas = new ArrayList<>();
		for (Relacao relacao : relacoes) {
			disciplinas.add(relacao.getDisciplina());
		}
		
		List<Disciplina> disciplinasResultados = new ArrayList<>();
		for(Resultado resultado: resultados) {
			disciplinasResultados.add(resultado.getDisciplina());
		}
		
		if(!disciplinasResultados.containsAll(disciplinas)){
			return "Não avaliada";
		}
		
		return "Avaliada";
	}
	
}
