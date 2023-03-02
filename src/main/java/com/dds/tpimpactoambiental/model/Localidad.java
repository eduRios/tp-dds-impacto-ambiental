package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Localidad")
@Table(name = "localidades")
@Immutable
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Localidad extends BaseGeoDato {

    private String codigoPostal;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "municipio", nullable = false, foreignKey = @ForeignKey(name = "FK_Localidades_Municipio"))
    private Municipio municipio;

    @OneToMany(mappedBy = "localidad", cascade = CascadeType.ALL)
    private List<Direccion> direcciones = new ArrayList<>();

    public Localidad() {
    }

    public Localidad(int idSegunApi, String nombre, String codigoPostal) {
        super(idSegunApi, nombre);
        this.codigoPostal = codigoPostal;
    }

    public void addDireccion(Direccion direccion) {
        direcciones.add(direccion);
        direccion.setLocalidad(this);
    }

    public void addDirecciones(List<Direccion> direcciones) {
        direcciones.forEach(this::addDireccion);
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + this.getNombre() +
                ", (CP: " + codigoPostal +
                "), "+this.getMunicipio().getNombre()+
                ", "+this.getMunicipio().getProvincia().getNombre();
    }
}
