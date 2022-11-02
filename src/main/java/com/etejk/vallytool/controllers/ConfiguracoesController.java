package com.etejk.vallytool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.TrimestreAtualRepository;
import com.etejk.vallytool.repositories.UsuarioRepository;

@Controller
@RequestMapping("configuracoes")
public class ConfiguracoesController {
	
	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	TrimestreAtualRepository tar;
	
	@GetMapping()
	public String configuracoes(Authentication auth,
								Model model) {
		Usuario usuario = ur.findByNome(auth.getName());
		model.addAttribute("usuario", usuario);
		model.addAttribute("trimestre", tar.getTrimestreAtual());
		return "site/configuracoes";
	}
	
	@PostMapping("/atualizar-nome")
	public String atualizarNome(@RequestParam(name = "id") String id,
								@RequestParam(name = "nome") String nome) {
		Usuario usuario = ur.findById(Integer.parseInt(id)).get();
		usuario.setNome(nome);
		ur.save(usuario);
		
		return "redirect:/configuracoes";
	}
	
	@PostMapping("/atualizar-email")
	public String atualizarEmail(@RequestParam(name = "id") String id,
								@RequestParam(name = "nome") String email) {
		Usuario usuario = ur.findById(Integer.parseInt(id)).get();
		usuario.setEmail(email);
		ur.save(usuario);
		
		return "redirect:/configuracoes";
	}
}
