package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.model.Cantidad;
import com.dds.tpimpactoambiental.model.FactorDeEmision;
import com.dds.tpimpactoambiental.model.TipoMedioDeTransporte;
import com.dds.tpimpactoambiental.model.Unidad;
import com.dds.tpimpactoambiental.repository.TipoMedioDeTransporteRepository;
import com.dds.tpimpactoambiental.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class TipoMedioDeTransporteService {

    @Autowired
    TipoMedioDeTransporteRepository repository;
    @Autowired
    private UnidadRepository unidadRepository;

    public TipoMedioDeTransporte getByNombre(String nombre) {
        return repository.getByNombre(nombre);
    }

    @Transactional
    public void seedData() {
        if (repository.hasData()) {
            //log.debug("Seed: ya hay TiposMedioDeTransporte creados");
            return;
        }

        //log.debug("Seed: se crean los TiposMedioDeTransporte iniciales");

        Unidad GCO2eq_SOBRE_KM = unidadRepository.getBySimbolo("gCO2eq/km").get();

        TipoMedioDeTransporte tren = new TipoMedioDeTransporte("Tren");
        tren.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 1)));

        TipoMedioDeTransporte subte = new TipoMedioDeTransporte("Subte");
        subte.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 1)));

        TipoMedioDeTransporte colectivo = new TipoMedioDeTransporte("Colectivo");
        colectivo.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 1)));

        TipoMedioDeTransporte bicicleta = new TipoMedioDeTransporte("Bicicleta");
        bicicleta.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 0)));

        TipoMedioDeTransporte aPie = new TipoMedioDeTransporte("A pie");
        aPie.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 0)));

        TipoMedioDeTransporte monopatin = new TipoMedioDeTransporte("Monopatín");
        monopatin.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 0)));

        TipoMedioDeTransporte moto = new TipoMedioDeTransporte("Moto");
        moto.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 1)));

        TipoMedioDeTransporte auto = new TipoMedioDeTransporte("Auto");
        auto.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 1)));

        TipoMedioDeTransporte camioneta = new TipoMedioDeTransporte("Camioneta");
        camioneta.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 1)));

        TipoMedioDeTransporte camionDeCarga = new TipoMedioDeTransporte("Camión de carga");
        camionDeCarga.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 1)));

        TipoMedioDeTransporte utilitarioLiviano = new TipoMedioDeTransporte("Utilitario liviano");
        utilitarioLiviano.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 1)));

        repository.saveAll(Arrays.asList(tren, subte, colectivo)); // Transportes publicos
        repository.saveAll(Arrays.asList(bicicleta, aPie, monopatin)); // No contaminantes
        repository.saveAll(Arrays.asList(moto, auto, camioneta, camionDeCarga, utilitarioLiviano)); // Vehiculos particulares
    }
}
