package com.dds.tpimpactoambiental.repository;


import com.dds.tpimpactoambiental.model.Espacio;
import com.dds.tpimpactoambiental.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class EspacioRepository extends BaseRepository<Espacio> {

    public EspacioRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Espacio> getEntityClass() {
        return Espacio.class;
    }
}
