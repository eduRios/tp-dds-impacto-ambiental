package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.Localidad;
import com.dds.tpimpactoambiental.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class LocalidadRepository extends BaseRepository<Localidad> {

    public LocalidadRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Localidad getByNombre(String nombre) {
        String query = "from Localidad l " +
                "where l.nombre = :nombre";
        return entityManager.createQuery(query, Localidad.class)
                .setParameter("nombre", nombre)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Class<Localidad> getEntityClass() {
        return Localidad.class;
    }
}
