 package com.etejk.vallytool.controllers;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.etejk.vallytool.dao.UsuarioDAO;
import com.etejk.vallytool.entities.Disciplina;
import com.etejk.vallytool.entities.Relacao;
import com.etejk.vallytool.entities.RoleModel;
import com.etejk.vallytool.entities.RoleName;
import com.etejk.vallytool.entities.Turma;
import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.DisciplinaRepository;
import com.etejk.vallytool.repositories.RelacaoRepository;
import com.etejk.vallytool.repositories.RoleRepository;
import com.etejk.vallytool.repositories.TurmaRepository;
import com.etejk.vallytool.repositories.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	TurmaRepository tr;
	
	@Autowired
	RoleRepository rr;
	
	@Autowired
	RelacaoRepository rer;

	@Autowired
	DisciplinaRepository dr;

	@PostMapping("usuarios/update")
	public String updateUsuario(Model model,@RequestParam(name = "id") String id,
								@RequestParam(name = "role") String role) {
		
		Optional<Usuario> usuario = ur.findById(Integer.parseInt(id));
		if(!usuario.isPresent()) {
			return "redirect:/usuarios/dados";
		}
		List<RoleModel> roles = new ArrayList<>();
		RoleModel roleModel = rr.findByRoleName(RoleName.valueOf(role));
		if(roleModel == null) {
			return "redirect:/usuarios/dados";
		}
		roles.add(roleModel);
		usuario.get().setRoles(roles);
		
		ur.save(usuario.get());
		return "redirect:/usuarios/dados?id=" + id;
		
	}
	@PostMapping("usuarios")
	public String saveUsuario(@Valid UsuarioDAO usuarioDAO) {
		System.out.println(usuarioDAO);
		
		
		List<RoleModel> findAllRoles = rr.findAll();
		List<RoleModel> roles = new ArrayList<>();
		for (RoleModel role : findAllRoles) {
			if(role.getRoleName().name().equals(usuarioDAO.getRole())) {
				roles.add(role);
			}
		}
		
		System.out.println(roles.get(0).getRoleName().name());
		
		Usuario usuarioOriginal = new Usuario(
				usuarioDAO.getCpf().replace(".", "").replace("-", ""),
				usuarioDAO.getNome(),
				usuarioDAO.getEmail(),
				usuarioDAO.senha(),
				roles
				);
		try {
		ur.save(usuarioOriginal);
		} catch (Exception e){
			return "redirect:/usuarios/error";
		}
		return "redirect:/inicio";
	}
	
	@GetMapping("inicio")
	public String inicio(Model model, @Param("search") String search) {
		if(search != null) {
			model.addAttribute("usuarios", ur.search(search));
		}else {
		model.addAttribute("usuarios", ur.findAll());
		}
		
		return "site/inicio";
	}
	
	@GetMapping("usuarios/error")
	public ModelAndView usuarioError(ModelMap model){
		model.addAttribute("error", "Usuario já cadastrado!");
		return new ModelAndView("redirect:/inicio", model);
	}
	
	@GetMapping("usuarios/vinculos")
    public String vinculos(Model model, @RequestParam(name = "id") String id,
    		@Param(value = "turma") String turma) {

        Optional<Usuario> user = ur.findById(Integer.parseInt(id));
        if(!user.isPresent() || user.get().getAuthority().equals("SOP")) {
            return "redirect:/inicio";
        };

        Usuario usuario = user.get();
        List<Turma> turmas = tr.findAll();
        List<Relacao> relacoes = rer.findByUsuario(usuario);
        List<Disciplina> disciplinasRelacao = new ArrayList<>();
        for(Relacao relacao: relacoes) {
        	disciplinasRelacao.add(relacao.getDisciplina());
        }
        
        turmas.removeAll(usuario.getTurmas());
        
        if(turma != null) {
        	Turma turmaFoda = tr.findByCodigo(turma);
        	turmaFoda.getDisciplinas().removeAll(disciplinasRelacao);
        	model.addAttribute("turmaSolicitada", turmaFoda);
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("turmas", turmas);
        model.addAttribute("relacoes", relacoes);
        model.addAttribute("turmasUsuario", usuario.getTurmas());
        
        return "site/vinculos";
    }
	
	
	@PostMapping("usuarios/vinculos")
	public String salvarVinculo(@RequestParam(name = "id") String id, @RequestParam(name ="turmas") List<String> turmas) {

        Optional<Usuario> user = ur.findById(Integer.parseInt(id));
        if(!user.isPresent()) {
            return "redirect:/inicio";
        };
        
        Usuario usuario = user.get();
        
        for(String turma: turmas) {
        	usuario.addTurma(tr.findByCodigo(turma));
        }
        
        ur.save(usuario);
        return "redirect:/usuarios/vinculos?id=" + id + "&etapa=0";
        
	}
	
	@PostMapping("usuarios/relacao")
	public String relacionar(@RequestParam(name = "id") String id,
							@RequestParam(name = "turma") String turma,
							@RequestParam(name = "disciplinas") List<String> disciplinas) {
		
		Usuario usuario = ur.findById(Integer.parseInt(id)).get();		
		Turma turmaEnt = tr.findByCodigo(turma);
		List<Disciplina> disciplinaEnt = new ArrayList<>();
		for (String disciplina: disciplinas) {
			disciplinaEnt.add(dr.findByNome(disciplina));	
		}
		for(Disciplina disciplina: disciplinaEnt) {
		Relacao relacao = new Relacao(turmaEnt, disciplina, usuario);
			rer.save(relacao);
		}
		
		return "redirect:/usuarios/vinculos?id" + id;
		
	}
	@GetMapping("usuarios/dados")
    public String dados(Model model, @RequestParam(name = "id") String id) {

        Optional<Usuario> user = ur.findById(Integer.parseInt(id));
        if(!user.isPresent()) {
            return "redirect:/inicio";
        };

        model.addAttribute("usuario", user.get());
        return "site/dados";
    }
}

