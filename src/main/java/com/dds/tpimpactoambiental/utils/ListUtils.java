package com.dds.tpimpactoambiental.utils;


import com.dds.tpimpactoambiental.dtos.IdTextPair;
import com.dds.tpimpactoambiental.model.BaseEntity;
import com.dds.tpimpactoambiental.model.Medicion;

import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {

    public static <T> T getLast(List<T> list) {
        return list.get(list.size() - 1);
    }

    public static <T extends BaseEntity> List<IdTextPair> toIdTextPairList(List<T> entities) {
        return entities.stream()
                .map(IdTextPair::new)
                .collect(Collectors.toList());
    }

    public static Medicion getMedicionConTipoConsumo(List<Medicion> mediciones, String nombreTipoConsumo) {
        return mediciones.stream()
                .filter(medicion -> medicion.getTipoConsumo().getNombre().equals(nombreTipoConsumo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No hay ninguna Medicion con el TipoConsumo '" + nombreTipoConsumo + "'"));
    }

}
