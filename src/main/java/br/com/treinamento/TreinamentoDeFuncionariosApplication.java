package br.com.treinamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class })
public class TreinamentoDeFuncionariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreinamentoDeFuncionariosApplication.class, args);
	}

}
