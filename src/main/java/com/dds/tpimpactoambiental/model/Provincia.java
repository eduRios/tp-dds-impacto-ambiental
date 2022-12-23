package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Provincia")
@Table(name = "provincias")
@Immutable
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Provincia extends BaseGeoDato {

    @OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL)
    private List<Municipio> municipios = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "pais", nullable = false, foreignKey = @ForeignKey(name = "FK_Provincias_Pais"))
    private Pais pais;

    public Provincia() {
    }

    public Provincia(int idSegunApi, String nombre) {
        super(idSegunApi, nombre);
    }

    public void addMunicipio(Municipio municipio) {
        municipios.add(municipio);
        municipio.setProvincia(this);
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
