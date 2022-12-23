package com.dds.tpimpactoambiental.repository;


import com.dds.tpimpactoambiental.model.Provincia;
import com.dds.tpimpactoambiental.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ProvinciaRepository extends BaseRepository<Provincia> {

    public ProvinciaRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Provincia> getEntityClass() {
        return Provincia.class;
    }
}
