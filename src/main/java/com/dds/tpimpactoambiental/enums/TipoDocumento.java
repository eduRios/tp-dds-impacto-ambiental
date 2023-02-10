package com.dds.tpimpactoambiental.enums;

public enum TipoDocumento {
    DNI,
    PASAPORTE,
    LIBRETA_CIVICA,
    LIBRETA_DE_ENROLAMIENTO;

    private static final TipoDocumento[] values = values();

    public static TipoDocumento getFromOrdinal(long ordinal) {
        return values[(int) ordinal];
    }
}
