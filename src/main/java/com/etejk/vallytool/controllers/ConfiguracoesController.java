package com.etejk.vallytool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView atualizarNome(ModelMap model, @RequestParam(name = "id") String id,
								@RequestParam(name = "nome") String nome) {
		Usuario usuario = ur.findById(Integer.parseInt(id)).get();
		if(usuario == null) {
			model.addAttribute("error", "Usuário inválido!");
			return new ModelAndView("redirect:/configuracoes", model);
		}
		
		usuario.setNome(nome);
		try {
		ur.save(usuario);
		}catch(Exception e) {
			model.addAttribute("error", "Oops! Algo deu errado!");
			return new ModelAndView("redirect:/configuracoes", model);
		}
		
		model.addAttribute("sucess", "Nome altedado!");
		return new ModelAndView("redirect:/configuracoes", model);
	}
	
	@PostMapping("/atualizar-email")
	public ModelAndView atualizarEmail(ModelMap model, @RequestParam(name = "id") String id,
								@RequestParam(name = "email") String email) {
		Usuario usuario = ur.findById(Integer.parseInt(id)).get();
		if(usuario == null) {
			model.addAttribute("error", "Usuário inválido!");
			return new ModelAndView("redirect:/configuracoes", model);
		}
		usuario.setEmail(email);
		try {
		ur.save(usuario);
		}catch(Exception e) {
			model.addAttribute("error", "Oops! Algo deu errado!");
			return new ModelAndView("redirect:/configuracoes", model);
		}
		
		model.addAttribute("sucess", "Email alterado!");
		return new ModelAndView("redirect:/configuracoes", model);
	}
	
	@PostMapping("/atualizar-senha")
	public ModelAndView atualizarSenha(ModelMap model, @RequestParam(name = "id") String id,
								@RequestParam(name = "senha") String senha) {
		Usuario usuario = ur.findById(Integer.parseInt(id)).get();
		if(usuario == null) {
			model.addAttribute("error", "Usuário inválido!");
			return new ModelAndView("redirect:/configuracoes", model);
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setSenha(encoder.encode(senha));
		
		try {
		ur.save(usuario);
		}catch(Exception e) {
			model.addAttribute("error", "Oops! Algo deu errado!");
			return new ModelAndView("redirect:/configuracoes", model);
		}
		
		model.addAttribute("sucess", "Email alterado!");
		return new ModelAndView("redirect:/configuracoes", model);
	}
}
