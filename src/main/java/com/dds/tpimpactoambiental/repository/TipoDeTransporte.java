package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.TipoMedioDeTransporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDeTransporte extends JpaRepository<TipoMedioDeTransporte,Long> {
}
