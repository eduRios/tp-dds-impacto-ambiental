package com.dds.tpimpactoambiental.service.calculodistancias;

import com.dds.tpimpactoambiental.model.*;
import com.dds.tpimpactoambiental.service.UnidadService;
import com.dds.tpimpactoambiental.service.calculodistancias.apidistancias.GeoService;
import com.dds.tpimpactoambiental.service.calculodistancias.apidistancias.dtos.DistanciaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CalculadoraDistancias {
    @Autowired
    private GeoService geoService;
    @Autowired
    private UnidadService unidadService;


    public Cantidad calcularDistanciaTransportePublico(Parada paradaInicial, Parada paradaFinal) {
        Cantidad distanciaRecorrida = new Cantidad(unidadService.getBySimbolo("km").get(), 0);
        Parada paradaActual = paradaInicial;
        while (!paradaActual.equals(paradaFinal)) {
            distanciaRecorrida = distanciaRecorrida.add(paradaActual.getDistanciaParadaSiguiente());
            paradaActual = paradaActual.getParadaSiguiente();
        }
        return distanciaRecorrida;
    }

    public Cantidad calcularDistanciaConGeoService(Lugar lugarInicio, Lugar lugarFin) {
        try {
            Direccion direccionInicio = lugarInicio.getDireccion();
            Direccion direccionFin = lugarFin.getDireccion();

            int localidadInicioId = direccionInicio.getLocalidad().getIdSegunApi();
            int localidadFinId = direccionFin.getLocalidad().getIdSegunApi();

            DistanciaDto distanciaDto = geoService.getDistancia(localidadInicioId, direccionInicio.getCalle(), direccionInicio.getAltura(), localidadFinId, direccionFin.getCalle(), direccionFin.getAltura());
            Unidad unidadDistancia = unidadService.getBySimbolo(distanciaDto.getUnidad().toLowerCase()).get();
            Unidad KM = unidadService.getBySimbolo("km").get();
            return new Cantidad(unidadDistancia, distanciaDto.getValor()).toUnidad(KM);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
