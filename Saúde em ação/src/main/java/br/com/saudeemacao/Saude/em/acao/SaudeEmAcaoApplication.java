package br.com.saudeemacao.Saude.em.acao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.saudeemacao.Saude.em.acao") // Garante que o Spring escaneie esse pacote e seus subpacotes
public class SaudeEmAcaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaudeEmAcaoApplication.class, args);
	}
}
