package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.dtos.MiembroDto;
import com.dds.tpimpactoambiental.dtos.ResponseWithResults;
import com.dds.tpimpactoambiental.dtos.request.RequestRegistrarMiembro;
import com.dds.tpimpactoambiental.dtos.response.Response;
import com.dds.tpimpactoambiental.dtos.response.ResponseRegistrarMiembro;
import com.dds.tpimpactoambiental.model.Miembro;
import com.dds.tpimpactoambiental.model.Persona;
import com.dds.tpimpactoambiental.model.Sector;
import com.dds.tpimpactoambiental.model.Solicitud;
import com.dds.tpimpactoambiental.repository.MemberRepository;
import com.dds.tpimpactoambiental.repository.PersonaRepository;
import com.dds.tpimpactoambiental.repository.SectorRepository;
import com.dds.tpimpactoambiental.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MiembroService {

    @Autowired
    private SectorRepository sectorRepository;
    @Autowired
    private MemberRepository  memberRepository;
    @Autowired
    private SolicitudRepository solicitudRepository;
    @Autowired
    private PersonaRepository personaRepository;

    public ResponseEntity<Object> registrarMiembro(RequestRegistrarMiembro request){
        ResponseRegistrarMiembro response = new ResponseRegistrarMiembro();
        Persona persona = personaRepository.getById(request.getPersona().getId());
        if (persona == null) {
            response.setMessage("No existe ninguna Persona con el ID especificado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Optional<Sector> sector = sectorRepository.findById(request.getSector().getId());
        if (!sector.isPresent() || sector.get().getOrganizacion().getId() != request.getOrganizacion().getId()) {
            response.setMessage("El sector no se encontro, o no pertenece a la organizacion especificada");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
        }
        Miembro miembro = new Miembro(persona);
        memberRepository.save(miembro);
        Solicitud solicitud = new Solicitud(miembro, sector.get());
        solicitudRepository.save(solicitud);
        response.setMessage("Solicitud enviada");
        response.setIdSolicitud(solicitud.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseWithResults<MiembroDto> listarMiembros() {
        List<MiembroDto> dtos = memberRepository.findAll().stream()
                .map(MiembroDto::from)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, dtos);
    }

    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
