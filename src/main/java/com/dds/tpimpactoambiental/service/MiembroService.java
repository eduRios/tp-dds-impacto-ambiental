package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.dtos.request.RequestRegistrarMiembro;
import com.dds.tpimpactoambiental.dtos.response.Response;
import com.dds.tpimpactoambiental.dtos.response.ResponseRegistrarMiembro;
import com.dds.tpimpactoambiental.model.Miembro;
import com.dds.tpimpactoambiental.model.Sector;
import com.dds.tpimpactoambiental.model.Solicitud;
import com.dds.tpimpactoambiental.repository.MemberRepository;
import com.dds.tpimpactoambiental.repository.SectorRepository;
import com.dds.tpimpactoambiental.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MiembroService {

    @Autowired
    private SectorRepository sectorRepository;
    @Autowired
    private MemberRepository  memberRepository;

    @Autowired
    private SolicitudRepository solicitudRepository;


    public ResponseEntity<Object> registrarMiembro(RequestRegistrarMiembro request){
        ResponseRegistrarMiembro response = new ResponseRegistrarMiembro();
        Miembro miembro = new Miembro(request.getNombre(), request.getApellido(), request.getTipoDocumento(), request.getNroDocumento());
        Optional<Sector> sector = sectorRepository.findById(request.getIdSector());
        if (!sector.isPresent() || sector.get().getOrganizacion().getId() != request.getIdOrganizacion()) {
            response.setMessage("El sector no se encontro, o no pertenece a la organizacion especificada");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
        }
        memberRepository.save(miembro);
        Solicitud solicitud = new Solicitud(miembro, sector.get());
        solicitudRepository.save(solicitud);
        response.setMessage("Solicitud enviada");
        response.setIdSolicitud(solicitud.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @Transactional
    public void seedData() {
        Miembro cosme = new Miembro("Cosme","Fulanito","DNI","12345678");
        Miembro bari = new Miembro("Bari","Davinson","DNI","98765432");

        Sector sector = sectorRepository.findByNombre("Sector TEST");
        sector.addMiembro(cosme);
        cosme.setFechaIngreso(LocalDate.now());
        sector.addMiembro(bari);
        bari.setFechaIngreso(LocalDate.now());
        memberRepository.saveAll(Arrays.asList(cosme,bari));
    }

    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
