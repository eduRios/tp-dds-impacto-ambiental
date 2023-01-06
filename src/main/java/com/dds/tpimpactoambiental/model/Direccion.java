package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity(name = "Direccion")
@Table(name = "direcciones")
@Immutable
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Direccion extends BaseEntity {

    private String calle;
    private String altura;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "localidad", nullable = false, foreignKey = @ForeignKey(name = "FK_Direcciones_Localidad"))
    private Localidad localidad;

    public Direccion() {
    }

    public Direccion(String calle, String altura) {
        this.calle = calle;
        this.altura = altura;
    }

    public Municipio getMunicipio() {
        return localidad.getMunicipio();
    }

    public Provincia getProvincia() {
        return getMunicipio().getProvincia();
    }

    public Pais getPais() {
        return getProvincia().getPais();
    }

    public String getCodigoPostal() {
        return localidad.getCodigoPostal();
    }

    @Override
    public String toString() {
        return calle + altura + ", " + localidad.toString();
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }
}
