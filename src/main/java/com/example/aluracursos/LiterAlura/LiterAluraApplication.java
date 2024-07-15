package com.example.aluracursos.LiterAlura;

import com.example.aluracursos.LiterAlura.principal.Principal;
import com.example.aluracursos.LiterAlura.repository.IAutorRepository;
import com.example.aluracursos.LiterAlura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private ILibroRepository libroRepository;

	@Autowired
	private IAutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository,autorRepository);
		principal.muestraElMenu();
	}
}
