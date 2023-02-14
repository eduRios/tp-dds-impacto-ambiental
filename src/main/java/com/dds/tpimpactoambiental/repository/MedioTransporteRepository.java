package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.MedioTransporte;
import com.dds.tpimpactoambiental.model.TransportePublico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MedioTransporteRepository extends JpaRepository<MedioTransporte, Long> {
    Optional<TransportePublico> getByLinea(@Param("linea") String linea);

    @Query("SELECT (CASE WHEN COUNT(*) > 0 THEN true ELSE false END) AS EXISTE FROM MedioTransporte")
    boolean hasData();
}
