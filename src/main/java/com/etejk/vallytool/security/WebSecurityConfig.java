package com.etejk.vallytool.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.etejk.vallytool.entities.Usuario;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Autowired
	private javax.sql.DataSource dataSource;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().anyRequest().authenticated()
		.and()
			.formLogin(form -> form
					.loginPage("/login").permitAll())
				.logout().logoutRequestMatcher
				(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		
		auth.jdbcAuthentication()
		.dataSource(dataSource).
		passwordEncoder(encoder);
	}
	
}