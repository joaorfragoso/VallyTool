package com.etejk.vallytool.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login").permitAll()
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/images/**").permitAll()
                .antMatchers(HttpMethod.GET, "/styles/**").permitAll()
                .antMatchers(HttpMethod.GET, "/modules/**").permitAll()
                .antMatchers("/sop").hasRole("SOP")
                .antMatchers("/professor").hasRole("PROFESSOR")
                .antMatchers("/redefinir").hasAnyRole("PROFESSOR", "SOP")
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}