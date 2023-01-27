package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TipoUnidad")
@Table(name = "tipos_de_unidad")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipoUnidad extends BaseEntity {

    private String nombre;

    @OneToMany(mappedBy = "tipoUnidad", cascade = CascadeType.ALL)
    private List<Unidad> unidades = new ArrayList<>();

    public TipoUnidad() {
    }

    public TipoUnidad(String nombre) {
        this.nombre = nombre;
    }

    public void addUnidad(Unidad unidad) {
        unidades.add(unidad);
        unidad.setTipoUnidad(this);
    }

    public void addUnidades(List<Unidad> unidades) {
        unidades.forEach(this::addUnidad);
    }

    public Unidad getUnidadBase() {
        return unidades.stream()
                .filter(Unidad::isBase)
                .findFirst()
                .orElse(null);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Unidad> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidad> unidades) {
        this.unidades = unidades;
    }
}
