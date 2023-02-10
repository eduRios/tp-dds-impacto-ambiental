package com.dds.tpimpactoambiental.model;

import com.dds.tpimpactoambiental.enums.Clasificacion;
import com.dds.tpimpactoambiental.enums.TipoOrganizacion;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="organizacion")
public class Organizacion extends BaseEntity{
    private String razonSocial;
    private TipoOrganizacion tipoOrganizacion;
    private Clasificacion clasificacion;

    private int cantDiasHabilesPorSemana;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factor_k", nullable = false, foreignKey = @ForeignKey(name = "FK_Organizaciones_FactorK"))
    private Cantidad factorK;

    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL)
    private List<Sector> sectores = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "organizacion_id")
    private List<DatosActividad> datosActividadList;

    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL)
    private List<Medicion> mediciones = new ArrayList<>();

    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contacto> contactos = new ArrayList<>();

    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL)
    private List<RegistroCalculoHCDatoActividad> registrosCalculoHCDatoActividad = new ArrayList<>();

    public Organizacion() {
    }

    public Organizacion(String razonSocial, TipoOrganizacion tipoOrganizacion, Clasificacion clasificacion,
                        Cantidad factorK, int cantDiasHabilesPorSemana) {
        this.razonSocial = razonSocial;
        this.tipoOrganizacion = tipoOrganizacion;
        this.clasificacion = clasificacion;
        this.factorK = factorK;
        this.cantDiasHabilesPorSemana = cantDiasHabilesPorSemana;
    }

    public String getRazonSocial() {
        return razonSocial;
    }
    public List<Sector> getSectores() {
        return sectores;
    }

    public void setSectores(List<Sector> sectores2) {
        this.sectores = sectores2;
    }

    public List<DatosActividad> getDatosActividadList() {
        return datosActividadList;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getCantDiasHabilesPorSemana() {
        return cantDiasHabilesPorSemana;
    }

    public void setCantDiasHabilesPorSemana(int cantDiasHabilesPorSemana) {
        this.cantDiasHabilesPorSemana = cantDiasHabilesPorSemana;
    }

    public Cantidad getFactorK() {
        return factorK;
    }

    public void setFactorK(Cantidad factorK) {
        this.factorK = factorK;
    }

    public void setDatosActividadList(List<DatosActividad> datosActividadList) {
        this.datosActividadList = datosActividadList;
    }

    public List<Medicion> getMediciones() {
        return mediciones;
    }

    public void setMediciones(List<Medicion> mediciones) {
        this.mediciones = mediciones;
    }

    public TipoOrganizacion getTipoOrganizacion() {
        return tipoOrganizacion;
    }

    public void setTipoOrganizacion(TipoOrganizacion tipoOrganizacion) {
        this.tipoOrganizacion = tipoOrganizacion;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public List<RegistroCalculoHCDatoActividad> getRegistrosCalculoHCDatoActividad() {
        return registrosCalculoHCDatoActividad;
    }

    public void setRegistrosCalculoHCDatoActividad(List<RegistroCalculoHCDatoActividad> registrosCalculoHCDatoActividad) {
        this.registrosCalculoHCDatoActividad = registrosCalculoHCDatoActividad;
    }

    public void addSector(Sector sector) {
        sectores.add(sector);
        sector.setOrganizacion(this);
    }

    public void addContacto(Contacto contacto) {
        contactos.add(contacto);
        contacto.setOrganizacion(this);
    }

    public List<Miembro> getMiembros() { // Para saber los miembros que tiene una organizacion de cada sector que tiene
        return sectores.stream()
                .flatMap(s -> s.getMiembros().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Trayecto> getTrayectosRealizadosPorMiembrosEnFecha(LocalDate date) {
        return getMiembros().stream()
                .flatMap(miembro -> miembro.getTrayectosRealizadosEnFecha(date).stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Solicitud> getSolicitudes() {
        return sectores.stream()
                .flatMap(sector -> sector.getSolicitudes().stream())
                .collect(Collectors.toList());
    }

    public void addMediciones(List<Medicion> mediciones) {
        mediciones.forEach(this::addMedicion);
    }

    public void addMedicion(Medicion medicion) {
        mediciones.add(medicion);
        medicion.setOrganizacion(this);
    }

    public void addRegistroCalculoHC(RegistroCalculoHCDatoActividad registroCalculoHC) {
        registroCalculoHC.setOrganizacion(this);
        registrosCalculoHCDatoActividad.add(registroCalculoHC);
    }

    public List<Tramo> getTramosDeMiembros() {
        return getMiembros().stream()
                .flatMap(m -> m.getTramos().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return razonSocial;
    }
}
