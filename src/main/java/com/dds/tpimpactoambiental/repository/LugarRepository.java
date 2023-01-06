package com.dds.tpimpactoambiental.repository;


import com.dds.tpimpactoambiental.model.Lugar;
import com.dds.tpimpactoambiental.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class LugarRepository extends BaseRepository<Lugar> {

    public LugarRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Lugar> getEntityClass() {
        return Lugar.class;
    }
}
