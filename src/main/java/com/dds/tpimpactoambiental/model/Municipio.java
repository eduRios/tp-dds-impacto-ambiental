package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Municipio")
@Table(name = "municipios")
@Immutable
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Municipio extends BaseGeoDato {

    @OneToMany(mappedBy = "municipio", cascade = CascadeType.ALL)
    private List<Localidad> localidades = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "provincia", nullable = false, foreignKey = @ForeignKey(name = "FK_Municipios_Provincia"))
    private Provincia provincia;

    public Municipio() {
    }

    public Municipio(int idSegunApi, String nombre) {
        super(idSegunApi, nombre);
    }

    public void addLocalidad(Localidad localidad) {
        localidades.add(localidad);
        localidad.setMunicipio(this);
    }

    public List<Localidad> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(List<Localidad> localidades) {
        this.localidades = localidades;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
}
