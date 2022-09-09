package com.etejk.vallytool.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.View;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.services.UsuarioService;
@Controller
@RequestMapping(value= "/api")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public Page<Usuario> findUsuarios(Pageable pageable){
		ModelAndView mv = new ModelAndView();
		return service.findUsuarios(pageable);
	}
	
	@GetMapping(value= "list")
	public List<Usuario> findUsuarios(){
		return service.findUsuarios();	
	}
	
	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		
		model.addAttribute("usuarios", service.findUsuarios());
		return "usuarios";
	}
	
	@GetMapping("/cargo")
	public Page<Usuario> findUsuarios(
			@RequestParam(value= "cargo", defaultValue = "") Character cargo,
			Pageable pageable){
		return service.findUsuarios(cargo, pageable);	
	}
}
