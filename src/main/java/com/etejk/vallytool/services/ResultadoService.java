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

	public boolean verificar(LocalDate data, Trimestre trimestre, Resultado resultado) {
 		List<Resultado> resultadosAnoTrimestre = rr.findAnoTrimestre(data.getYear(), trimestre);

		if (resultadosAnoTrimestre == null) {
			return true;
		}

		for (Resultado resultadoNew : resultadosAnoTrimestre) {
			if (resultadoNew.getTurma() == resultado.getTurma()
					&& resultadoNew.getDisciplina() == resultado.getDisciplina()) {
				return false;
			}

		}

		return true;
	}
}
