package com.etejk.vallytool.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	ImplementsUserDetailsService userDetailsService = new ImplementsUserDetailsService();
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    			.httpBasic()
    			.and()
    			.authorizeHttpRequests()
    			.anyRequest().authenticated()
    			.and()
    			.csrf().disable();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth
    	.userDetailsService(userDetailsService)
    	.passwordEncoder(passwordEncoder());
    }
    
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}