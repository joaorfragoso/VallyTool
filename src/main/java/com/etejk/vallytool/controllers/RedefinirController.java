package com.etejk.vallytool.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.etejk.vallytool.dao.PasswordDAO;
import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.TrimestreAtualRepository;
import com.etejk.vallytool.repositories.UsuarioRepository;
import com.etejk.vallytool.services.EmailService;
import com.etejk.vallytool.services.ResetService;
import com.etejk.vallytool.services.UsuarioService;

@Controller
@RequestMapping("redefinir")
public class RedefinirController {
	
	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	ResetService rs;
	
	@Autowired
	EmailService es;
	
	@Autowired
	UsuarioService us;
	
	@Autowired
	TrimestreAtualRepository tar;
	@GetMapping()
	public String redefinir() {
		return "site/redefinir_senha";
	}
	
	@PostMapping()
	public ModelAndView verificarCpf(HttpServletRequest request, ModelMap model, @RequestParam("cpf") String cpf) {
		String cpfParsed = cpf.replace("-", "").replace(".", "");
		Usuario user = ur.findByCpf(cpfParsed);
		if(user == null) {
			model.addAttribute("error");
			return new ModelAndView("redirect:/redefinir", model);
		};
		
		String token = UUID.randomUUID().toString();
		rs.createPasswordToken(token, user);
		
		String url = "http://localhost:8080" + "/redefinir/atualizarSenha?token=" + token;
		try {
		es.sendEmail(user.getEmail(), url);
		}catch(Exception e) {
			model.addAttribute("error", "Algo deu errado, tente novamente.");
			return new ModelAndView("redirect:/redefinir", model);
		}
		model.addAttribute("sucess", "Um email foi enviado para você contendo as informações sobre a redefinição de senha");
		model.addAttribute("email", user.getEmail());
		return new ModelAndView("redirect:/redefinir", model);
	}
	
	@GetMapping("/atualizarSenha")
	public String atualizarSenha(Model model, @RequestParam("token") String token) {
		if(rs.validatePasswordToken(token) != null) {
			return "redirect:/redefinir/invalido";
		}else{
			model.addAttribute("token", token);
		}
		model.addAttribute("trimestre", tar.getTrimestreAtual());
		
		return "site/atualizar_senha";
	}
	
	@PostMapping("/atualizarSenha")
	public ModelAndView atualizarSenha(ModelMap model, PasswordDAO passwordDAO) {
		if(rs.validatePasswordToken(passwordDAO.getToken()) != null) {
			return new ModelAndView("redirect:/redefinir/invalido");
		}
		
		Usuario usuario = rs.findUserByToken(passwordDAO.getToken());
		if(usuario != null) {
			us.changeUserPassword(usuario, passwordDAO.getSenha());
			return new ModelAndView("redirect:/redefinir/sucesso");
		}
		
		return new ModelAndView("redirect:/redefinir/invalidUser");
	}
	
	@GetMapping("/invalido")
	public ModelAndView invalido(ModelMap model) {
		model.addAttribute("error", "Token Inválido");
		return new ModelAndView("redirect:/login" , model);
	}
	
	@GetMapping("/invalidUser")
	public ModelAndView usuarioInvalido(ModelMap model) {
		model.addAttribute("error", "Usuario Inválido");
		System.out.println("invalid");
		return new ModelAndView("redirect:/login" , model);
		
	}
	
	@GetMapping("/sucesso")
	public ModelAndView sucesso(ModelMap model) {
		model.addAttribute("sucess", "Senha trocada com sucesso");
		return new ModelAndView("redirect:/login" , model);
	}
	
}
