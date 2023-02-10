package com.dds.tpimpactoambiental.enums;

public enum TipoOrganizacion {
    GUBERNAMENTAL,
    ONG,
    EMPRESA,
    INSTITUCION;

    private static final TipoOrganizacion[] values = values();

    public static TipoOrganizacion getFromOrdinal(long ordinal) {
        return values[(int) ordinal];
    }
}
