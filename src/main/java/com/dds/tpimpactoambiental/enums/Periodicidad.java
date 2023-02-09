package com.dds.tpimpactoambiental.enums;

public enum Periodicidad {
    MENSUAL,
    ANUAL;

    public static Periodicidad from(String periodicidad) {
        switch (periodicidad.toUpperCase()) {
            case "ANUAL":
                return ANUAL;
            case "MENSUAL":
                return MENSUAL;
            default:
                throw new IllegalArgumentException("No se reconoce la periodicidad: " + periodicidad);
        }
    }
}
