package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.Municipio;
import com.dds.tpimpactoambiental.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MunicipioRepository extends BaseRepository<Municipio> {

    public MunicipioRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Municipio> getEntityClass() {
        return Municipio.class;
    }
}
