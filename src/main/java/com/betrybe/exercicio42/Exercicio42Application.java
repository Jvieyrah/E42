package com.betrybe.exercicio42;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.betrybe.exercicio42.models.entities")
@EnableJpaRepositories("com.betrybe.exercicio42.models.repositories")
@ComponentScan("com.betrybe.exercicio42")
public class Exercicio42Application {

	public static void main(String[] args) {
		SpringApplication.run(Exercicio42Application.class, args);
	}

}
