package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Pais")
@Table(name = "paises")
@Immutable
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pais extends BaseGeoDato {

    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
    private List<Provincia> provincias = new ArrayList<>();

    public Pais() {
    }

    public Pais(int idSegunApi, String nombre) {
        super(idSegunApi, nombre);
    }

    public void addProvincia(Provincia provincia) {
        provincias.add(provincia);
        provincia.setPais(this);
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }
}
