package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.dtos.IdTextPair;
import com.dds.tpimpactoambiental.dtos.ResponseWithResults;
import com.dds.tpimpactoambiental.dtos.SectorTerritorialConHC;
import com.dds.tpimpactoambiental.enums.TipoSectorTerritorial;
import com.dds.tpimpactoambiental.model.AgenteSectorial;
import com.dds.tpimpactoambiental.model.Organizacion;
import com.dds.tpimpactoambiental.model.SectorTerritorial;
import com.dds.tpimpactoambiental.repository.AgenteSectorialRepository;
import com.dds.tpimpactoambiental.repository.OrganizationRepository;
import com.dds.tpimpactoambiental.repository.SectorTerritorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectorTerritorialService {

    @Autowired
    private SectorTerritorialRepository sectorTerritorialRepository;
    @Autowired
    private AgenteSectorialRepository repository;
    @Autowired
    private OrganizationRepository organizationRepository;

    public List<SectorTerritorial> getAll() {
        return sectorTerritorialRepository.findAll();
    }
    public SectorTerritorial getById(long id) {
        return sectorTerritorialRepository.getById(id);
    }
    public ResponseWithResults<IdTextPair> getAllDtos() {
        List<IdTextPair> dtos = sectorTerritorialRepository.findAll().stream()
                .map(sectorTerritorial -> new IdTextPair(sectorTerritorial.getId(),sectorTerritorial.getNombre()))
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, dtos);
    }

    private SectorTerritorialConHC createDtoFromEntity(SectorTerritorial sectorTerritorial) {
        return SectorTerritorialConHC.from(sectorTerritorial);
    }
    @Transactional
    public void seedData() {

        SectorTerritorial sectorTerritorial = new SectorTerritorial("Buenos Aires", TipoSectorTerritorial.Provincias);
        Organizacion organizacion = organizationRepository.findByRazonSocial("Organizacion TEST");
        sectorTerritorial.addOrganizacion(organizacion);

        sectorTerritorialRepository.save(sectorTerritorial);
        AgenteSectorial agenteSectorial = new AgenteSectorial(sectorTerritorial,"Cosme", "Fulanito");

        repository.saveAll(Arrays.asList(agenteSectorial));
    }
}
