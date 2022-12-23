package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.Trayecto;
import com.dds.tpimpactoambiental.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class TrayectoRepository extends BaseRepository<Trayecto> {

    public TrayectoRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Trayecto> getEntityClass() {
        return Trayecto.class;
    }
}
