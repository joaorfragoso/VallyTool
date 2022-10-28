package com.etejk.vallytool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etejk.vallytool.repositories.UsuarioRepository;

@Service
public class EmailService {	
	
	@Autowired
	private JavaMailSender emailSender;
	
	@GetMapping("email")
	public String sendEmail(String email, String url) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		try { 
			message.setFrom("vallytool@gmail.com");
			message.setTo(email);
			message.setSubject("Redefina sua senha");
			message.setText("Redefina sua senha " + url);
			emailSender.send(message);
		}catch(MailException e) {
			System.out.println(e.getMessage());
		}
	
		return message.toString();
	}
}
