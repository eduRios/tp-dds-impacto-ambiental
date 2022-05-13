package com.dds.tpimpactoambiental;

import com.dds.tpimpactoambiental.model.Usuario;
import com.dds.tpimpactoambiental.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TpImpactoAmbientalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpImpactoAmbientalApplication.class, args);// por ahora no descomentar ni borrar!
	}

	@Bean
	public CommandLineRunner initData(UserRepository userRepository){
		return (args) -> {
			Usuario usuario= new Usuario("bari","wowsosmivida12");
			userRepository.save(usuario);
		};
	}

}
