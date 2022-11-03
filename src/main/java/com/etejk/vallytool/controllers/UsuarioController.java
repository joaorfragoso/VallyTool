package com.etejk.vallytool.controllers;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
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
import com.etejk.vallytool.repositories.TrimestreAtualRepository;
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

	@Autowired
	TrimestreAtualRepository tar;

	@PostMapping("usuarios/update")
	public ModelAndView updateUsuario(Authentication auth, ModelMap model, @RequestParam(name = "id") String id,
			@RequestParam(name = "role") String role) {

		Optional<Usuario> usuario = ur.findById(Integer.parseInt(id));
		if (!usuario.isPresent() || usuario.get().getNome() == auth.getName()) {
			model.addAttribute("error", "Algo deu errado.");
			return new ModelAndView("redirect:/usuarios/dados", model);
		}

		List<RoleModel> roles = new ArrayList<>();
		RoleModel roleModel = rr.findByRoleName(RoleName.valueOf(role));
		if (roleModel == null) {
			model.addAttribute("error", "Cargo inválido.");
			return new ModelAndView("redirect:/usuarios/dados", model);
		}

		if (roleModel.getRoleName().equals(RoleName.ROLE_SOP)) {
			List<Relacao> relacoes = rer.findByUsuario(usuario.get());
			for (Relacao relacao : relacoes) {
				try {
					rer.delete(relacao);
				} catch (Exception e) {
					model.addAttribute("error", "Algo deu errado");
					return new ModelAndView("redirect:/usuarios/error", model);
				}
			}

			usuario.get().getDisciplinas().clear();
			usuario.get().getTurmas().clear();
		}

		roles.add(roleModel);
		usuario.get().setRoles(roles);

		ur.save(usuario.get());
		model.addAttribute("sucess", "Usuário atualizado!");
		return new ModelAndView("redirect:/usuarios/dados?id=" + id, model);

	}

	@PostMapping("usuarios")
	public ModelAndView saveUsuario(ModelMap model, @Valid UsuarioDAO usuarioDAO) {
		System.out.println(usuarioDAO);

		List<RoleModel> findAllRoles = rr.findAll();
		List<RoleModel> roles = new ArrayList<>();
		for (RoleModel role : findAllRoles) {
			if (role.getRoleName().name().equals(usuarioDAO.getRole())) {
				roles.add(role);
			}
		}

		System.out.println(roles.get(0).getRoleName().name());

		Usuario usuarioOriginal = new Usuario(usuarioDAO.getCpf().replace(".", "").replace("-", ""),
				usuarioDAO.getNome(), usuarioDAO.getEmail(), usuarioDAO.senha(), roles);
		try {
			ur.save(usuarioOriginal);
		} catch (Exception e) {
			model.addAttribute("error", "Algo deu errado");
			return new ModelAndView("redirect:/usuarios/error", model);
		}

		model.addAttribute("sucess", "Usuário cadastrado!");
		return new ModelAndView("redirect:/inicio", model);
	}

	@GetMapping("inicio")
	public String inicio(Model model, @Param("search") String search) {
		if (search != null) {
			model.addAttribute("usuarios", ur.search(search));
		} else {
			model.addAttribute("usuarios", ur.findAll());
		}
		model.addAttribute("trimestre", tar.getTrimestreAtual());

		return "site/inicio";
	}

	@GetMapping("usuarios/error")
	public ModelAndView usuarioError(ModelMap model) {
		model.addAttribute("error", "Usuario já cadastrado!");
		return new ModelAndView("redirect:/inicio", model);
	}

	@GetMapping("usuarios/vinculos")
	public String vinculos(Model model, @RequestParam(name = "id") String id, @Param(value = "turma") String turma) {

		Optional<Usuario> user = ur.findById(Integer.parseInt(id));
		if (!user.isPresent() || user.get().getAuthority().equals("SOP")) {
			return "redirect:/inicio";
		}
		;
		Turma turmaEnt = tr.findByCodigo(turma);
		Usuario usuario = user.get();
		List<Turma> turmas = tr.findAll();
		turmas.removeAll(usuario.getTurmas());
		List<Relacao> relacoes = rer.findByTurmaAndUsuario(turmaEnt, usuario);
		for (Relacao relacao : relacoes) {
			turmaEnt.getDisciplinas().remove(relacao.getDisciplina());
		}
		List<Relacao> turmaRelacao = rer.findByTurma(turmaEnt);
		for (Relacao relacao : turmaRelacao) {
			turmaEnt.getDisciplinas().remove(relacao.getDisciplina());
		}
		model.addAttribute("turmaSolicitada", turmaEnt);
		model.addAttribute("relacoes", relacoes);
		model.addAttribute("trimestre", tar.getTrimestreAtual());
		model.addAttribute("usuario", usuario);
		model.addAttribute("turmas", turmas);
		model.addAttribute("turmasUsuario", usuario.getTurmas());

		return "site/vinculos";
	}

	@PostMapping("usuarios/vinculos")
	public ModelAndView salvarVinculo(ModelMap model, @RequestParam(name = "id") String id,
			@RequestParam(name = "turmas") List<String> turmas) {

		Optional<Usuario> user = ur.findById(Integer.parseInt(id));
		if (!user.isPresent()) {
			model.addAttribute("error", "Usuário inexistente");
			return new ModelAndView("redirect:/inicio");
		}
		Usuario usuario = user.get();

		for (String turma : turmas) {
			try {
			usuario.addTurma(tr.findByCodigo(turma));
			} catch(Exception e){
				model.addAttribute("error", "Turma inexistente");
				return new ModelAndView("redirect:/inicio");
			}
		}

		ur.save(usuario);
		model.addAttribute("sucess", "Vínculo feito");
		return new ModelAndView("redirect:/usuarios/vinculos?id=" + id + "&etapa=0", model);

	}

	@PostMapping("usuarios/relacao")
	public ModelAndView relacionar(ModelMap model, @RequestParam(name = "id") String id,
			@RequestParam(name = "turma") String turma, @RequestParam(name = "disciplinas") List<String> disciplinas) {

		Usuario usuario = ur.findById(Integer.parseInt(id)).get();
		if (usuario == null) {
			model.addAttribute("error", "Usuário inexistente");
			return new ModelAndView("redirect:/inicio");
		}
		Turma turmaEnt = tr.findByCodigo(turma);
		if (turmaEnt == null) {
			model.addAttribute("error", "Turma inexistente");
			return new ModelAndView("redirect:/inicio");
		}

		List<Disciplina> disciplinaList = new ArrayList<>();
		for (String disciplina : disciplinas) {

			Disciplina disciplinaEnt = dr.findByNome(disciplina);
			if (disciplinaEnt == null || turmaEnt.getDisciplinas().contains(disciplinaEnt)) {
				model.addAttribute("error", "Disciplinas inexistente");
				return new ModelAndView("redirect:/inicio");
			}
			disciplinaList.add(disciplinaEnt);

			if (!usuario.getDisciplinas().contains(disciplinaEnt)) {
				usuario.addDisciplina(disciplinaEnt);
			}

		}
		try {
			ur.save(usuario);
		} catch (Exception e) {
			model.addAttribute("error", "Algo deu errado");
			return new ModelAndView("redirect:/usuarios/error", model);
		}
		
		for (Disciplina disciplina : disciplinaList) {
			Relacao relacao = new Relacao(turmaEnt, disciplina, usuario);
			try {
				rer.save(relacao);
			} catch (Exception e) {
				model.addAttribute("error", "Algo deu errado");
				return new ModelAndView("redirect:/usuarios/error", model);
			}
		}
		tr.save(turmaEnt);
		model.addAttribute("sucess", "Professor relacionado!");
		return new ModelAndView("redirect:/usuarios/vinculos?id=" + id + "&turma=" + turma + "&etapa=1", model);

	}

	@PostMapping("usuarios/remover-turma")
	public ModelAndView removerTurma(ModelMap model, @RequestParam(name = "turma") String turma,
			@RequestParam(name = "id") String id) {

		Usuario usuario = ur.findById(Integer.parseInt(id)).get();
		if (usuario == null) {
			model.addAttribute("error", "Usuário inexistente");
			return new ModelAndView("redirect:/inicio");
		}
		Turma turmaEnt = tr.findByCodigo(turma);
		if (turmaEnt == null) {
			model.addAttribute("error", "Turma inexistente");
			return new ModelAndView("redirect:/inicio");
		}

		usuario.getTurmas().remove(turmaEnt);
		List<Relacao> relacoes = rer.findByTurmaAndUsuario(turmaEnt, usuario);
		for (Relacao relacao : relacoes) {
			try {
				rer.delete(relacao);
			} catch (Exception e) {
				model.addAttribute("error", "Algo deu errado");
				return new ModelAndView("redirect:/usuarios/error", model);
			}
		}
		try {
			ur.save(usuario);
		} catch (Exception e) {
			model.addAttribute("error", "Algo deu errado");
			return new ModelAndView("redirect:/usuarios/error", model);
		}
		model.addAttribute("sucess", "Turma removida!!");
		return new ModelAndView("redirect:/usuarios/vinculos?id=" + id + "&etapa=0", model);
	}

	@PostMapping("usuarios/remover-disciplina")
	public ModelAndView removerDisciplinaTurma(ModelMap model, @RequestParam(name = "turma") String turma,
			@RequestParam(name = "id") String id, @RequestParam(name = "disciplina") String disciplina) {

		Usuario usuario = ur.findById(Integer.parseInt(id)).get();
		if (usuario == null) {
			model.addAttribute("error", "Usuário inexistente");
			return new ModelAndView("redirect:/inicio");
		}
		Turma turmaEnt = tr.findByCodigo(turma);
		if (turmaEnt == null) {
			model.addAttribute("error", "Turma inexistente");
			return new ModelAndView("redirect:/inicio");
		}
		Disciplina disciplinaEnt = dr.findByNome(disciplina);
		if (disciplinaEnt == null) {
			model.addAttribute("error", "Disciplinas inexistente");
			return new ModelAndView("redirect:/inicio");
		}
		Relacao relacao = rer.findByEverything(turmaEnt, disciplinaEnt, usuario);
		try {
			rer.delete(relacao);
		} catch (Exception e) {
			model.addAttribute("error", "Algo deu errado");
			return new ModelAndView("redirect:/usuarios/error", model);
		}

		model.addAttribute("sucess", "Disciplina removida!");
		return new ModelAndView("redirect:/usuarios/vinculos?id=" + id + "&turma=" + turma + "&etapa=1", model);
	}

	@GetMapping("usuarios/dados")
	public String dados(Authentication auth, Model model, @RequestParam(name = "id") String id) {

		Optional<Usuario> user = ur.findById(Integer.parseInt(id));
		if (!user.isPresent()) {
			return "redirect:/inicio";
		}
		;
		model.addAttribute("trimestre", tar.getTrimestreAtual());
		model.addAttribute("usuarioLogado", auth.getName());
		model.addAttribute("usuario", user.get());
		return "site/dados";
	}
}
