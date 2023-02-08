package com.dds.tpimpactoambiental.repository;


import com.dds.tpimpactoambiental.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    @Query("SELECT s " +
            "FROM Solicitud s " +
            "WHERE s.sector.organizacion.id = :idOrganizacion ")
    List<Solicitud> solicitudesParaOrganizacion(@Param("idOrganizacion") long idOrganizacion);
}
