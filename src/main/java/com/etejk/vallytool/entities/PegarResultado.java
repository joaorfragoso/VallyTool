package com.etejk.vallytool.entities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.etejk.vallytool.repositories.ResultadoRepository;

public class PegarResultado {
	
	ResultadoRepository rr;
	
	public PegarResultado(ResultadoRepository rr) {
		this.rr = rr;
	}
	
	public List<Resultado> pegarResultado(Integer ano, Turma turma, String trimestre) {
		return rr.findByEverything(ano, Trimestre.valueOf(trimestre), turma);
	}
}
