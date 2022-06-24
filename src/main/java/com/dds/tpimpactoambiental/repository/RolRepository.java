package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByName(@Param("name") String name);
}
