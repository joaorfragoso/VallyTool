package com.etejk.vallytool.controllers;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etejk.vallytool.dao.ResultadoDAO;
import com.etejk.vallytool.entities.Disciplina;
import com.etejk.vallytool.entities.Resultado;
import com.etejk.vallytool.entities.Trimestre;
import com.etejk.vallytool.entities.Turma;
import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.DisciplinaRepository;
import com.etejk.vallytool.repositories.ProfessoRepository;
import com.etejk.vallytool.repositories.ResultadoRepository;
import com.etejk.vallytool.repositories.TurmaRepository;

@Controller
public class ResultadoController {
	
	@Autowired
	public ResultadoRepository rr;

	@Autowired
	private TurmaRepository tr;
	
	@Autowired
	private ProfessoRepository ur;
	
	@Autowired
	private DisciplinaRepository dr;
	
	@PostMapping("/avaliar")
	public String avaliar(ResultadoDAO resultado, Model model) {
		System.out.println(resultado);
		
		
		
		Turma turma = tr.findByCodigo(resultado.getTurma());
		Disciplina disciplina = dr.findByNome(resultado.getDisciplina());
		Usuario usuario = ur.findByNome(resultado.getUsuario());
		Resultado resultadoOriginal = new Resultado(turma,
				disciplina,
				resultado.addConceitos(),
				usuario,
				Trimestre.TERCEIRO,
				Year.parse(resultado.getAno()));
		
		
		if(verificar(resultadoOriginal.getAno(), Trimestre.TERCEIRO, resultadoOriginal)) 
		{
			System.out.println("Funcionouy porra!!!!@!!");
			rr.save(resultadoOriginal);
		}
		
		return "redirect:/avaliar_error";
		
	}
	
	public boolean verificar(Year ano,
			Trimestre trimestre,
			Resultado resultado) {
		List<Resultado> resultadoAno = rr.findByAno(ano);
		List<Resultado> resultadoTrimestre = rr.findByTrimestre(trimestre);
		
		Integer maior = 0;
		if(resultadoAno.size() > resultadoTrimestre.size()) {
			maior = resultadoAno.size();
		}else {
			maior = resultadoTrimestre.size();
		}
		
		if(maior == 0) {
			return true;
		}
		
		List<Resultado> resultados = new ArrayList<>();
		for(int i = 0; i < maior; i++) {
			if(resultadoAno.equals(resultadoTrimestre)) {
				resultados.add(resultadoAno.get(i));
				
				if(resultados.get(i).getTurma() == resultado.getTurma()&&
					resultados.get(i).getDisciplina() == resultado.getDisciplina()){
					return false;
				}
			}
			
		}
		
		return true;
	}
}
