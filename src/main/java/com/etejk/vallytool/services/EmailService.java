package com.etejk.vallytool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etejk.vallytool.repositories.UsuarioRepository;

@Controller
public class EmailService {	
	
	@Autowired
	private JavaMailSender emailSender;
	
	@GetMapping("email")
	public String sendEmail() {
		SimpleMailMessage message = new SimpleMailMessage();
		
		try { 
			message.setFrom("vallytool@gmail.com");
			message.setTo("joaofragoso376@gmail.com");
			message.setSubject("Teste");
			message.setText("Teste, cpf do primeiro usuario do banco: ");
			emailSender.send(message);
		}catch(MailException e) {
			System.out.println(e.getMessage());
		}
	
		return message.toString();
	}
}
