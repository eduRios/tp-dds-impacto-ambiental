package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.model.Matricula;
import com.dds.tpimpactoambiental.model.Miembro;
import com.dds.tpimpactoambiental.model.Organizacion;
import com.dds.tpimpactoambiental.repository.MatriculaRepository;
import com.dds.tpimpactoambiental.repository.MemberRepository;
import com.dds.tpimpactoambiental.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @RequestMapping(path = "/registro_org/{idMember}", method = RequestMethod.POST)
    public ResponseEntity<Object> registrarOrganizacion(@PathVariable Long idMember,@RequestBody Organizacion organizacion){
        Matricula matricula = new Matricula();
        Organizacion org = organizationRepository.findByRazonSocial(organizacion.getRazonSocial());
        matricula.setOrganizacion(org);
        Miembro miembro = memberRepository.findById(idMember).get();
        miembro.addMatricula(matricula);
        matriculaRepository.save(matricula);
        return new ResponseEntity<>(makeMap("matriculaId", matricula.getId()), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/registrar", method = RequestMethod.POST)
    public ResponseEntity<Object> registrarDatosPersonales(@RequestBody Miembro miembro){
        memberRepository.save(miembro);

        return new ResponseEntity<>(makeMap("miembroId", miembro.getId()), HttpStatus.CREATED);
    }
/*
    @RequestMapping(path = "/ver_org")
    public ResponseEntity<Object> getOrganizaciones(){
        List<Miembro> miembros = memberRepository.findAll();
        Miembro miembro = miembros.get(0);
        return new ResponseEntity<>(makeMap("miembroid 1", miembro), HttpStatus.CREATED);
    }*/

    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
