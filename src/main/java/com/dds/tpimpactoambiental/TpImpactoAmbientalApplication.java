package com.dds.tpimpactoambiental;

import com.dds.tpimpactoambiental.model.*;
import com.dds.tpimpactoambiental.repository.*;
import com.dds.tpimpactoambiental.service.*;
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
	public CommandLineRunner initData(RolRepository rolRepository, GeoService geoService, OrganizacionService organizacionService,
									  UnidadService unidadService, TipoMedioDeTransporteService tipoMedioDeTransporteService,
									  TransportePublicoService transportePublicoService, PersonaService personaService,
									  TipoConsumoService tipoConsumoService, SectorTerritorialService sectorTerritorialService){
		return (args) -> {
			Rol admin = new Rol("ROLE_ADMIN");
			Rol user = new Rol("ROLE_USER");
			rolRepository.save(admin);
			rolRepository.save(user);

			geoService.seedData();
			unidadService.seedData();
			tipoMedioDeTransporteService.seedData();
			transportePublicoService.seedData();
			tipoConsumoService.seedData();
			organizacionService.seedData();
			personaService.seedData();
			sectorTerritorialService.seedData();
		};
	}

}
