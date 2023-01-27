package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.enums.TipoCombustible;
import com.dds.tpimpactoambiental.model.*;
import com.dds.tpimpactoambiental.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TransportePublicoService {

    @Autowired
    private MedioTransporteRepository medioTransporteRepository;
    @Autowired
    private DireccionService direccionService;
    @Autowired
    private TipoMedioDeTransporteService tipoMedioDeTransporteService;
    @Autowired
    private CombustibleRepository combustibleRepository;
    @Autowired
    private ParadaRepository paradaRepository;
    @Autowired
    private UnidadRepository unidadRepository;
    @Autowired
    private LocalidadRepository localidadRepository;

/*
    @Transactional
    public BaseResponse crearTransportePublico(CrearTransportePublicoRequest request) {
        if (repository.getByLinea(request.getLinea()).isPresent()) {
            return new BaseResponse(HttpStatus.BAD_REQUEST, "Ya existe un TransportePublico con la linea especificada");
        }

        Combustible combustible = combustibleRepository.getById(request.getIdCombustible());

        List<Parada> paradas = new ArrayList<>();
        for (ParadaDto paradaDto : request.getParadas()) {
            Direccion direccion = direccionService.getOrCreateDireccion(paradaDto.getDireccion());

            Cantidad distanciaParadaSiguiente = null;
            if (paradaDto.getDistanciaParadaSiguiente() != null) {
                distanciaParadaSiguiente = new Cantidad(
                        unidadRepository.getById(paradaDto.getDistanciaParadaSiguiente().getUnidad().getId()),
                        paradaDto.getDistanciaParadaSiguiente().getValor()
                );
            }

            Parada parada = new Parada(direccion, distanciaParadaSiguiente);
            paradaRepository.save(parada);
            paradas.add(parada);
        }

        TransportePublico transportePublico = new TransportePublico(
                request.getLinea(),
                combustible,
                request.getCombustibleConsumidoPorKm()
        );

        TipoMedioDeTransporte tipoMedioDeTransporte = tipoMedioDeTransporteService.getById(request.getTipoMedioDeTransporte().getId());
        tipoMedioDeTransporte.addMedioDeTransporte(transportePublico);

        // Le seteo la Parada siguiente a cada Parada y la agrego al transportePublico
        for (int i = 0; i < paradas.size(); i++) {
            Parada parada = paradas.get(i);
            if (i < paradas.size() - 1) {
                parada.setParadaSiguiente(paradas.get(i + 1));
            }
            transportePublico.addParada(parada);
        }

        this.save(transportePublico);
        return new BaseResponse(HttpStatus.CREATED);
    }*/

    @Transactional
    public void seedData() {
        if (medioTransporteRepository.hasData()) {
            //log.debug("Seed: ya hay TransportesPublicos creados");
            return;
        }

        //log.debug("Seed: se crean los TransportesPublicos iniciales");

        TipoMedioDeTransporte colectivo = tipoMedioDeTransporteService.getByNombre("Colectivo");

        TransportePublico linea15 = new TransportePublico(
                "15", new Combustible(TipoCombustible.NAFTA, unidadRepository.getBySimbolo("m3").get()),
                10);
        colectivo.addMedioDeTransporte(linea15);

        Localidad localidadVillaCrespo = localidadRepository.getByNombre("VILLA CRESPO");

        Unidad KM = unidadRepository.getBySimbolo("km").get();

        Direccion direccion1 = new Direccion("Scalabrini Ortiz", "100");
        Cantidad distanciaParadaSiguiente1 = new Cantidad(KM, 0.1);
        Parada parada1 = new Parada(direccion1, distanciaParadaSiguiente1);

        Direccion direccion2 = new Direccion("Scalabrini Ortiz", "200");
        Cantidad distanciaParadaSiguiente2 = new Cantidad(KM, 0.2);
        Parada parada2 = new Parada(direccion2, distanciaParadaSiguiente2);

        Direccion direccion3 = new Direccion("Scalabrini Ortiz", "400");
        Cantidad distanciaParadaSiguiente3 = new Cantidad(KM, 0.3);
        Parada parada3 = new Parada(direccion3, distanciaParadaSiguiente3);

        Direccion direccion4 = new Direccion("Scalabrini Ortiz", "700");
        Parada parada4 = new Parada(direccion4, null);

        localidadVillaCrespo.addDirecciones(Arrays.asList(direccion1, direccion2, direccion3, direccion4));

        parada1.setParadaSiguiente(parada2);
        parada2.setParadaSiguiente(parada3);
        parada3.setParadaSiguiente(parada4);

        linea15.addParadas(Arrays.asList(parada1, parada2, parada3, parada4));

        medioTransporteRepository.saveAll(Arrays.asList(linea15));
    }
}
