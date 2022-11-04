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
                .formLogin().defaultSuccessUrl("/inicio", true)
                .loginPage("/login").failureUrl("/login-error").permitAll()
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/images/**").permitAll()
                .antMatchers(HttpMethod.GET, "/styles/**").permitAll()
                .antMatchers(HttpMethod.GET, "/modules/**").permitAll()
                .antMatchers(HttpMethod.GET, "/redefinir/**").permitAll()
                .antMatchers(HttpMethod.POST, "/redefinir/**").permitAll()
                .antMatchers(HttpMethod.GET, "/common/**").permitAll()
                .antMatchers(HttpMethod.GET, "/login/**").permitAll()
                .antMatchers(HttpMethod.POST, "/login/**").permitAll()
                .antMatchers(HttpMethod.GET, "/avaliar/**").hasRole("PROFESSOR")
                .antMatchers(HttpMethod.POST, "/avaliar/**").hasRole("PROFESSOR")
                .antMatchers(HttpMethod.GET, "/configuracoes/**").hasAnyRole("PROFESSOR", "SOP")
                .antMatchers(HttpMethod.POST, "/configuraceos/**").hasAnyRole("PROFESSOR", "SOP")
                .antMatchers(HttpMethod.GET, "/usuarios/**").hasRole("SOP")
                .antMatchers(HttpMethod.POST, "/usuarios/**").hasRole("SOP")
                .antMatchers(HttpMethod.GET, "/turmas/**").hasRole("SOP")
                .antMatchers(HttpMethod.POST, "/turmas/**").hasRole("SOP")
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