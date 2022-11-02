package com.etejk.vallytool.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.etejk.vallytool.repositories.TrimestreAtualRepository;

@Configuration
@EnableScheduling
public class TrocarTrimestre {
	@Autowired
	TrimestreAtualRepository tar;
	
	@Scheduled(cron = "0 0 0 1 1 *", zone = "America/Sao_Paulo")
	public void trocarTrimestre() {
		tar.resetar();
		System.out.println("Trimestre resetado com sucesso!");
	}
}
