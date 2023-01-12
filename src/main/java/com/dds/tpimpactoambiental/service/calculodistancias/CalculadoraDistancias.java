package com.dds.tpimpactoambiental.service.calculodistancias;

import com.dds.tpimpactoambiental.model.Direccion;
import com.dds.tpimpactoambiental.model.Lugar;
import com.dds.tpimpactoambiental.service.calculodistancias.apidistancias.GeoService;
import com.dds.tpimpactoambiental.service.calculodistancias.apidistancias.dtos.DistanciaDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CalculadoraDistancias {
    private final GeoService geoService;
    //private final UnidadService unidadService;

    public CalculadoraDistancias(GeoService geoService) {
        this.geoService = geoService;
        //this.unidadService = unidadService;
    }
/*
    public Cantidad calcularDistanciaTransportePublico(Parada paradaInicial, Parada paradaFinal) {
        Cantidad distanciaRecorrida = new Cantidad(unidadService.getBySimbolo("km").get(), 0);
        Parada paradaActual = paradaInicial;
        while (!paradaActual.equals(paradaFinal)) {
            distanciaRecorrida = distanciaRecorrida.add(paradaActual.getDistanciaParadaSiguiente());
            paradaActual = paradaActual.getParadaSiguiente();
        }
        return distanciaRecorrida;
    }
*/
    public void calcularDistanciaConGeoService(Lugar lugarInicio, Lugar lugarFin) {
        try {
            Direccion direccionInicio = lugarInicio.getDireccion();
            Direccion direccionFin = lugarFin.getDireccion();

            int localidadInicioId = direccionInicio.getLocalidad().getIdSegunApi();
            int localidadFinId = direccionFin.getLocalidad().getIdSegunApi();

            DistanciaDto distanciaDto = geoService.getDistancia(localidadInicioId, direccionInicio.getCalle(), direccionInicio.getAltura(), localidadFinId, direccionFin.getCalle(), direccionFin.getAltura());
            //Unidad unidadDistancia = unidadService.getBySimbolo(distanciaDto.getUnidad().toLowerCase()).get();
            //Unidad KM = unidadService.getBySimbolo("km").get();
            //new Cantidad(unidadDistancia, distanciaDto.getValor()).toUnidad(KM);
            //return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
