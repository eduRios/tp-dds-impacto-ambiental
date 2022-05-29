package com.dds.tpimpactoambiental;

import com.dds.tpimpactoambiental.model.Matricula;
import com.dds.tpimpactoambiental.model.Miembro;
import com.dds.tpimpactoambiental.model.Organizacion;
import com.dds.tpimpactoambiental.model.Usuario;
import com.dds.tpimpactoambiental.repository.MatriculaRepository;
import com.dds.tpimpactoambiental.repository.MemberRepository;
import com.dds.tpimpactoambiental.repository.OrganizationRepository;
import com.dds.tpimpactoambiental.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class TpImpactoAmbientalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpImpactoAmbientalApplication.class, args);// por ahora no descomentar ni borrar!
	}

	@Bean
	public CommandLineRunner initData(UserRepository userRepository, MemberRepository memberRepository, OrganizationRepository organizationRepository, MatriculaRepository matriculaRepository ){
		return (args) -> {
			Usuario usuario= new Usuario("bari","wowesmivida12");
			Miembro cosme = new Miembro("Cosme","Fulanito","DNI","12345678");
			Miembro jhon = new Miembro("Jhon","Wick","DNI","87654321");
			Miembro bari = new Miembro("Bari","Davinson","DNI","98765432");
			Organizacion coders = new Organizacion("the coders","Empresa", Arrays.asList("Tecnologia","RRHH"),"Software factory");
			Organizacion asesinos = new Organizacion("Asesinos","SA", Arrays.asList("Logistica","RRHH"),"Agencia de asesinos");
			Organizacion bovina = new Organizacion("Universidad bovina","Institución", Arrays.asList("Centro de estudiantes","Direccion"),"Universidad");

			Matricula matricula1 = new Matricula(cosme,coders);
			Matricula matricula2 = new Matricula(cosme,bovina);
			Matricula matricula3 = new Matricula(jhon,asesinos);
			Matricula matricula4 = new Matricula(bari,coders);

			memberRepository.save(cosme);
			memberRepository.save(jhon);
			memberRepository.save(bari);
			organizationRepository.save(coders);
			organizationRepository.save(asesinos);
			organizationRepository.save(bovina);
			matriculaRepository.save(matricula1);
			matriculaRepository.save(matricula2);
			matriculaRepository.save(matricula3);
			matriculaRepository.save(matricula4);
			userRepository.save(usuario);
		};
	}

}
