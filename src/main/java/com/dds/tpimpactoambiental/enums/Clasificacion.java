package com.dds.tpimpactoambiental.enums;

public enum Clasificacion {
    MINISTERIO,
    UNIVERSIDAD,
    ESCUELA,
    EMPRESA_SECTOR_PRIMARIO,
    EMPRESA_SECTOR_SECUNDARIO;

    private static final Clasificacion[] values = values();

    public static Clasificacion getFromOrdinal(long ordinal) {
        return values[(int) ordinal];
    }
}