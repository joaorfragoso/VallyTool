package com.etejk.vallytool.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etejk.vallytool.entities.Resultado;
import com.etejk.vallytool.entities.Trimestre;
import com.etejk.vallytool.repositories.ResultadoRepository;

@Service
public class ResultadoService {
	
	@Autowired
	ResultadoRepository rr;
	public boolean verificar(LocalDate data,
			Trimestre trimestre,
			Resultado resultado) {
		List<Resultado> resultadosAno = rr.findAno(data.getYear());
		List<Resultado> resultadoTrimestre = rr.findByTrimestre(trimestre);
		
		Integer maior = 0;
		if(resultadosAno.size() > resultadoTrimestre.size()) {
			maior = resultadosAno.size();
		}else {
			maior = resultadoTrimestre.size();
		}
		
		if(maior == 0) {
			return true;
		}
		
		List<Resultado> resultados = new ArrayList<>();
		for(int i = 0; i < maior; i++) {
			if(resultadosAno.equals(resultadoTrimestre)) {
				resultados.add(resultadosAno.get(i));
				
				if(resultados.get(i).getTurma() == resultado.getTurma()&&
					resultados.get(i).getDisciplina() == resultado.getDisciplina()){
					return false;
				}
			}
			
		}
		
		return true;
	}
}
