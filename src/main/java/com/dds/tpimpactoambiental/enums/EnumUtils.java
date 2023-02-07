package com.dds.tpimpactoambiental.enums;


import com.dds.tpimpactoambiental.dtos.IdTextPair;

public class EnumUtils {

    public static <E extends Enum<E>> IdTextPair enumToIdTextPair(E _enum) {
        return new IdTextPair(_enum.ordinal(), _enum.toString());
    }

}
