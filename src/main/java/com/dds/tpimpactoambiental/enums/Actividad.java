package com.dds.tpimpactoambiental.enums;

import java.text.Normalizer;
import java.util.stream.Stream;

public enum Actividad {
    COMBUSTION_FIJA("Combustión fija"),
    COMBUSTION_MOVIL("Combustión móvil"),
    ELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA("Electricidad adquirida y consumida"),
    LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS("Logística de productos y residuos");

    private final String nombre;

    Actividad(String nombre) {
        this.nombre = nombre;
    }

    public static Actividad from(String nombre) {
        return Stream.of(Actividad.values())
                .filter(actividad -> normalize(actividad.nombre).equals(normalize(nombre)))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No se reconoce la actividad: " + nombre));
    }

    private static String normalize(String nombre){
        return Normalizer.normalize(nombre, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
    @Override
    public String toString() {
        return nombre;
    }
}
