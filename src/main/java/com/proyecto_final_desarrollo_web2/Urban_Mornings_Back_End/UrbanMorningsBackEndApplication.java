package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UrbanMorningsBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrbanMorningsBackEndApplication.class, args);
	}

		@Bean
		CommandLineRunner initData(PasswordEncoder encoder) {
			return args -> {
				System.out.println("HASH VÁLIDO: " + encoder.encode("admin"));
			};
		}
	}

