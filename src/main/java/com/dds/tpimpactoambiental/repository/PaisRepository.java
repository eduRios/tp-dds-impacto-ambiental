package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.Pais;
import com.dds.tpimpactoambiental.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PaisRepository extends BaseRepository<Pais> {

    public PaisRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Pais> getEntityClass() {
        return Pais.class;
    }
}
