package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.TipoConsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TipoConsumoRepository extends JpaRepository<TipoConsumo, Long> {

    Optional<TipoConsumo> getByNombre(@Param("nombre") String simbolo);

    @Query("SELECT (CASE WHEN COUNT(*) > 0 THEN true ELSE false END) AS EXISTE FROM TipoConsumo")
    boolean hasData();
}
