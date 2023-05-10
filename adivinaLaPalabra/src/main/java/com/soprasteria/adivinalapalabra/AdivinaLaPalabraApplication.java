package com.soprasteria.adivinalapalabra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AdivinaLaPalabraApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdivinaLaPalabraApplication.class, args);
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//System.out.println(encoder.encode("pepe7890"));
	}
}
