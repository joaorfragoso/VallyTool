package com.etejk.vallytool.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

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
import com.etejk.vallytool.services.ResultadoService;

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
	
	@Autowired
	private ResultadoService rs;
	
	@PostMapping("/avaliar")
	public String avaliar(@Valid ResultadoDAO resultado, Model model) {
		System.out.println(resultado);
		
		
		
		Turma turma = tr.findByCodigo(resultado.getTurma());
		Disciplina disciplina = dr.findByNome(resultado.getDisciplina());
		Usuario usuario = ur.findByNome(resultado.getUsuario());
		Resultado resultadoOriginal = new Resultado(turma,
				disciplina,
				resultado.addConceitos(),
				usuario,
				Trimestre.TERCEIRO);
		
		
		if(rs.verificar(resultadoOriginal.getData(), Trimestre.TERCEIRO, resultadoOriginal)) 
		{
			rr.save(resultadoOriginal);
		} else {			
			return "redirect:/avaliar_error";
		}
		
		return "redirect:/avaliar";
	}
	
}
