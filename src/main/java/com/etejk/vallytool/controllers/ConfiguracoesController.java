package com.etejk.vallytool.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.etejk.vallytool.entities.PasswordResetToken;
import com.etejk.vallytool.entities.Relacao;
import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.RelacaoRepository;
import com.etejk.vallytool.repositories.ResetRepository;
import com.etejk.vallytool.repositories.ResultadoRepository;
import com.etejk.vallytool.repositories.TrimestreAtualRepository;
import com.etejk.vallytool.repositories.UsuarioRepository;

@Controller
@RequestMapping("configuracoes")
public class ConfiguracoesController {
	
	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	TrimestreAtualRepository tar;
	
	@Autowired
	RelacaoRepository rer;
	
	ResetRepository resr;
	@GetMapping()
	public String configuracoes(Authentication auth,
								Model model) {
		Usuario usuario = ur.findByNome(auth.getName());
		model.addAttribute("usuario", usuario);
		model.addAttribute("trimestre", tar.getTrimestreAtual());
		return "site/configuracoes";
	}
	
	@PostMapping("/atualizar-nome")
	public ModelAndView atualizarNome(@AuthenticationPrincipal User user, ModelMap model, @RequestParam(name = "id") String id,
								@RequestParam(name = "nome") String nome) {
		Usuario usuario = ur.findById(Integer.parseInt(id)).get();
		if(usuario == null) {
			model.addAttribute("error", "Usuário Inválido!");
			return new ModelAndView("redirect:/configuracoes", model);
		}
		
		usuario.setNome(nome);
		try {
		ur.save(usuario);
		}catch(Exception e) {
			model.addAttribute("error", "Nome já esta sendo usado!");
			return new ModelAndView("redirect:/configuracoes", model);
		}
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(usuario.getNome(), usuario.getPassword(), usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		model.addAttribute("sucess", "Nome Alterado!");
		return new ModelAndView("redirect:/configuracoes", model);
	}
	
	@PostMapping("/atualizar-email")
	public ModelAndView atualizarEmail(ModelMap model, @RequestParam(name = "id") String id,
								@RequestParam(name = "email") String email) {
		Usuario usuario = ur.findById(Integer.parseInt(id)).get();
		if(usuario == null) {
			model.addAttribute("error", "Usuário Inválido!");
			return new ModelAndView("redirect:/configuracoes", model);
		}
		usuario.setEmail(email);
		try {
		ur.save(usuario);
		}catch(Exception e) {
			model.addAttribute("error", "Oops! Algo deu errado!");
			return new ModelAndView("redirect:/configuracoes", model);
		}
		
		model.addAttribute("sucess", "Email Alterado!");
		return new ModelAndView("redirect:/configuracoes", model);
	}
	
	@PostMapping("/atualizar-senha")
	public ModelAndView atualizarSenha(ModelMap model, @RequestParam(name = "id") String id,
								@RequestParam(name = "senha") String senha) {
		Usuario usuario = ur.findById(Integer.parseInt(id)).get();
		if(usuario == null) {
			model.addAttribute("error", "Usuário Inválido!");
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
		
		model.addAttribute("sucess", "Senha Alterada!");
		return new ModelAndView("redirect:/configuracoes", model);
	}
	
	@PostMapping("/deletar-conta")
	public ModelAndView deletarConta(ModelMap model, 
									@RequestParam(name = "id") String id) {
		Usuario usuario = ur.findById(Integer.parseInt(id)).get();
		if(usuario == null) {
			model.addAttribute("error", "Usuário Inválido!");
			return new ModelAndView("redirect:/configuracoes", model);
		}
		
		List<Relacao> relacaos = rer.findByUsuario(usuario);
		for (Relacao relacao : relacaos) {
			relacao.setUsuario(null);
			try {
			rer.save(relacao);
			}catch(Exception e) {
				model.addAttribute("error", "Algo deu errado.");
				return new ModelAndView("redirect:/usuarios", model);
			}
		}
		
		List<PasswordResetToken> tokens = resr.findByUsuario(usuario);
		for (PasswordResetToken passwordResetToken : tokens) {
			resr.delete(passwordResetToken);
		}
		
		try {
			ur.delete(usuario);
		}catch(Exception e) {
			model.addAttribute("error", "Algo deu errado.");
			return new ModelAndView("redirect:/usuarios", model);
		}
		
		model.addAttribute("sucess", "Conta Excluída :(");
		return new ModelAndView("redirect:/logout", model);
		
	}
}
