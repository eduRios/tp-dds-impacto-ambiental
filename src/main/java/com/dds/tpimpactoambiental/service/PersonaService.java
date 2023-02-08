package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.dtos.IdTextPair;
import com.dds.tpimpactoambiental.dtos.PersonaDto;
import com.dds.tpimpactoambiental.dtos.ResponseWithResults;
import com.dds.tpimpactoambiental.dtos.request.RequestCrearPersona;
import com.dds.tpimpactoambiental.dtos.response.Response;
import com.dds.tpimpactoambiental.enums.TipoDocumento;
import com.dds.tpimpactoambiental.model.Miembro;
import com.dds.tpimpactoambiental.model.Persona;
import com.dds.tpimpactoambiental.model.Sector;
import com.dds.tpimpactoambiental.model.Usuario;
import com.dds.tpimpactoambiental.repository.PersonaRepository;
import com.dds.tpimpactoambiental.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    private SectorRepository sectorRepository;
    @Autowired
    private UsuarioService usuarioService;


    @Transactional
    public Response crearPersona(RequestCrearPersona request) {
        TipoDocumento tipoDocumento = TipoDocumento.getFromOrdinal(request.getTipoDocumento().getId());
        Persona persona = new Persona(request.getNombre(), request.getApellido(), tipoDocumento, request.getDocumento());
        personaRepository.save(persona);
        return new Response( HttpStatus.CREATED,"persona creada");
    }

    @Transactional
    public ResponseWithResults<PersonaDto> listarPersonas() {
        List<PersonaDto> dtos = personaRepository.findAll().stream()
                .map(PersonaDto::from)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, dtos);
    }

    @Transactional
    public ResponseWithResults<IdTextPair> listarPersonasIdTextPair() {
        List<IdTextPair> idTextPairs = personaRepository.findAll().stream()
                .map(IdTextPair::new)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, idTextPairs);
    }

    @Transactional
    public ResponseWithResults<IdTextPair> listarTiposDeDocumento() {
        List<IdTextPair> tiposDeDocumento = Arrays.stream(TipoDocumento.values())
                .map(tipo -> new IdTextPair(tipo.ordinal(), tipo.toString()))
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, tiposDeDocumento);
    }

    @Transactional
    public void seedData() {
        if (personaRepository.hasData()) {
            //log.debug("Seed: ya hay Personas creadas");
            return;
        }

        //log.debug("Seed: se crean las Personas iniciales (con miembro y usuario)");

        Persona personaCosme = new Persona("Cosme","Fulanito", TipoDocumento.DNI, "12345678");
        Miembro miembroCosme = new Miembro(personaCosme);
        personaCosme.addMiembro(miembroCosme);

        Persona personaBari = new Persona("Bari","Davinson", TipoDocumento.DNI, "87654321");
        Miembro miembroBari = new Miembro(personaBari);
        personaBari.addMiembro(miembroBari);

        Persona personaLucas = new Persona("Lucas", "Esposito", TipoDocumento.DNI, "11223344");
        Miembro miembroLucas = new Miembro(personaLucas);
        personaLucas.addMiembro(miembroLucas);

        Sector sector = sectorRepository.getByNombre("Sector TEST");
        sector.addMiembro(miembroCosme);
        miembroCosme.setFechaIngreso(LocalDate.now());
        sector.addMiembro(miembroBari);
        miembroBari.setFechaIngreso(LocalDate.now());
        sector.addMiembro(miembroLucas);
        miembroLucas.setFechaIngreso(LocalDate.now());

        Usuario usuarioEchi = usuarioService.crearUsuario("cosmefula", "fula1927");
        miembroCosme.setUsuario(usuarioEchi);

        Usuario usuarioMili = usuarioService.crearUsuario("bari777", "wowesmivida12");
        miembroBari.setUsuario(usuarioMili);

        Usuario usuarioLucas = usuarioService.crearUsuario("lucasesposito", "lucasesposito");
        miembroLucas.setUsuario(usuarioLucas);

        personaRepository.saveAll(Arrays.asList(personaCosme, personaBari, personaLucas));
    }

}
