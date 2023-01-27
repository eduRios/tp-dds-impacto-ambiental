package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.TipoMedioDeTransporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TipoMedioDeTransporteRepository extends JpaRepository<TipoMedioDeTransporte,Long> {
    TipoMedioDeTransporte getByNombre(@Param("nombre") String nombre);

    @Query("SELECT (CASE WHEN COUNT(*) > 0 THEN true ELSE false END) AS EXISTE FROM TipoMedioDeTransporte")
    boolean hasData();

}
