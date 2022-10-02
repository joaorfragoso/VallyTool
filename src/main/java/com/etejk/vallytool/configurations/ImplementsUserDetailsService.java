package com.etejk.vallytool.configurations;
import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.UsuarioRepository;

@Service
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {
	
    final UsuarioRepository userRepository;

    public ImplementsUserDetailsService(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }
    
	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		Usuario usuario = userRepository.findByCpf(cpf);
		if(usuario == null) {
			System.out.println("puta que pariu");
			throw new UsernameNotFoundException("Usuario não encontrado");
		}
		return new User(usuario.getCpf(), usuario.getSenha(), true, true, true, true, usuario.getAuthorities());
	}

}
