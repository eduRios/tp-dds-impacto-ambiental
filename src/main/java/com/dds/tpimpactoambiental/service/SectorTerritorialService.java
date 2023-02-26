package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.enums.TipoSectorTerritorial;
import com.dds.tpimpactoambiental.model.AgenteSectorial;
import com.dds.tpimpactoambiental.model.Organizacion;
import com.dds.tpimpactoambiental.model.SectorTerritorial;
import com.dds.tpimpactoambiental.repository.AgenteSectorialRepository;
import com.dds.tpimpactoambiental.repository.OrganizationRepository;
import com.dds.tpimpactoambiental.repository.SectorTerritorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
public class SectorTerritorialService {

    @Autowired
    private SectorTerritorialRepository sectorTerritorialRepository;
    @Autowired
    private AgenteSectorialRepository repository;
    @Autowired
    private OrganizationRepository organizationRepository;

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
