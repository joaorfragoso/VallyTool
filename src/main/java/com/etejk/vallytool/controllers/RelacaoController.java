package com.etejk.vallytool.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.etejk.vallytool.entities.Disciplina;
import com.etejk.vallytool.entities.Relacao;
import com.etejk.vallytool.entities.Turma;
import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.DisciplinaRepository;
import com.etejk.vallytool.repositories.RelacaoRepository;
import com.etejk.vallytool.repositories.TurmaRepository;
import com.etejk.vallytool.repositories.UsuarioRepository;

public class RelacaoController {

	@Autowired
	RelacaoRepository rr;
	
	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	TurmaRepository tr;
	
	@Autowired
	DisciplinaRepository dr;

}
