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
		es.sendEmail(user.getEmail(), url);
		
		model.addAttribute("sucess", "Um email foi enviado para você contendo as informações sobre a redefinição de senha");
		model.addAttribute("email", user.getEmail());
		return new ModelAndView("redirect:/redefinir", model);
	}
	
	@GetMapping("/atualizarSenha")
	public String atualizarSenha(Model model, @RequestParam("token") String token) {
		if(rs.validatePasswordToken(token) == null) {
			return "redirect:/redefinir/invalido";
		}else{
			model.addAttribute("token", token);
		}
		
		return "site/atualizar_senha";
	}
	
	@PostMapping("/atualizarSenha")
	public String atualizarSenha(PasswordDAO passwordDAO) {
		if(rs.validatePasswordToken(passwordDAO.getToken()) == null) {
			return "redirect:/redefinir/invalido";
		}
		
		Usuario usuario = rs.findUserByToken(passwordDAO.getToken());
		if(usuario != null) {
			us.changeUserPassword(usuario, passwordDAO.getSenha());
			return "redirect:/redefinir/sucesso";
		}
		
		return "redirect:/redefinir/invalidUser";
	}
	
	@GetMapping("/invalido")
	public ModelAndView invalido(ModelMap model) {
		model.addAttribute("errorToken", "Token Inválido");
		System.out.println("certo");
		return new ModelAndView("redirect:/login" , model);
	}
	
	@GetMapping("/invalidUser")
	public ModelAndView usuarioInvalido(ModelMap model) {
		model.addAttribute("errorUsuario", "Usuario Inválido");
		System.out.println("invalid");
		return new ModelAndView("redirect:/login" , model);
		
	}
	
	@GetMapping("/sucesso")
	public ModelAndView sucesso(ModelMap model) {
		model.addAttribute("sucess", "Senha trocada com sucesso");
		System.out.println("sucess");
		return new ModelAndView("redirect:/login" , model);
	}
	
}
