package com.dds.tpimpactoambiental;

import com.dds.tpimpactoambiental.model.*;
import com.dds.tpimpactoambiental.repository.*;
import com.dds.tpimpactoambiental.service.MiembroService;
import com.dds.tpimpactoambiental.service.OrganizacionService;
import com.dds.tpimpactoambiental.service.calculodistancias.apidistancias.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class TpImpactoAmbientalApplication {


	public static void main(String[] args) {
		SpringApplication.run(TpImpactoAmbientalApplication.class, args);// por ahora no descomentar ni borrar!
	}

	@Bean
	public CommandLineRunner initData(UserRepository userRepository, RolRepository rolRepository, MedioTransporteRepository medioTransporteRepository,
									  TramoRepository tramoRepository, GeoService geoService, OrganizacionService organizacionService,
									  MiembroService miembroService){
		return (args) -> {
			Usuario usuario= new Usuario("bari","wowesmivida12");
			Rol admin = new Rol("ADMIN");
			Rol user = new Rol("USER");
			TransportePublico linea101= new TransportePublico("colectivo","101");
			ServicioContratado uber = new ServicioContratado("uber");
			Otro bicicleta = new Otro("bicicleta");

			userRepository.save(usuario);
			rolRepository.save(admin);
			rolRepository.save(user);
			medioTransporteRepository.save(linea101);
			medioTransporteRepository.save(uber);
			medioTransporteRepository.save(bicicleta);

			geoService.seedData();
			organizacionService.seedData();
			miembroService.seedData();
			//tramoRepository.save(tramo);
		};
	}

}
