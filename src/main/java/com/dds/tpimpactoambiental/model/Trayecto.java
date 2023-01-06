package com.dds.tpimpactoambiental.model;

import com.dds.tpimpactoambiental.utils.DateTimeUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "Trayecto")
@Table(name = "trayectos")
public class Trayecto extends BaseEntity {

    @ManyToOne
    @JoinColumn(
            name = "inicio",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_Trayectos_Inicio")
    )
    private Lugar inicio;

    @ManyToOne
    @JoinColumn(
            name = "fin",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_Trayectos_Fin")
    )
    private Lugar fin;

    @OneToMany(mappedBy = "trayecto", cascade = CascadeType.ALL)
    private List<MiembroPorTrayecto> miembrosPorTrayecto = new ArrayList<>();

    @OneToMany(mappedBy = "trayecto", cascade = CascadeType.ALL)
    private List<Tramo> tramos = new ArrayList<>();

    private LocalDate fechaInicio; // Inclusivo
    private LocalDate fechaFin; // NO inclusivo
/*
    @OneToMany(mappedBy = "trayecto", cascade = CascadeType.ALL)
    private List<RegistroCalculoHCTrayecto> registrosCalculoHCTrayectos = new ArrayList<>();
*/
    public Trayecto() {
    }

    public Trayecto(Lugar inicio, Lugar fin, LocalDate fechaInicio, LocalDate fechaFin) {
        this.inicio = inicio;
        this.fin = fin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
/*
    public Cantidad getDistanciaTotalRecorrida() {
        return tramos.stream()
                .map(Tramo::getDistanciaRecorrida)
                .reduce(Cantidad::add).get();
    }
*/
    public boolean seRealizaEnFecha(LocalDate fecha) {
        return DateTimeUtils.isAfterOrEqual(fecha, fechaInicio) && fecha.isBefore(fechaFin);
    }

    public void addTramo(Tramo tramo) {
        tramos.add(tramo);
        tramo.setTrayecto(this);
    }

    public void addTramos(List<Tramo> tramos) {
        tramos.forEach(this::addTramo);
    }

    public void addMiembro(MiembroPorTrayecto miembroPorTrayecto) {
        this.miembrosPorTrayecto.add(miembroPorTrayecto);
        miembroPorTrayecto.setTrayecto(this);
    }

    public void addMiembros(List<MiembroPorTrayecto> miembrosPorTrayecto) {
        miembrosPorTrayecto.forEach(this::addMiembro);
    }
/*
    public void addRegistroCalculoHCTrayecto(RegistroCalculoHCTrayecto registroCalculoHCTrayecto) {
        registrosCalculoHCTrayectos.add(registroCalculoHCTrayecto);
        registroCalculoHCTrayecto.setTrayecto(this);
    }

    public void addRegistrosCalculoHCTrayecto(List<RegistroCalculoHCTrayecto> registrosCalculoHCTrayecto) {
        registrosCalculoHCTrayecto.forEach(this::addRegistroCalculoHCTrayecto);
    }
*/
    public List<Tramo> getTramosDelMiembro(Miembro miembro) {
        return tramos.stream()
                .filter(tramo -> tramo.getMiembros().contains(miembro))
                .collect(Collectors.toList());
    }

    public double getPesoTrayectoDelMiembro(Miembro miembro) {
        return miembrosPorTrayecto.stream()
                .filter(miembroPorTrayecto -> miembroPorTrayecto.getMiembro().equals(miembro))
                .findFirst().get()
                .getPeso();
    }
/*
    public List<Miembro> getMiembrosDeOrganizacion(Organizacion organizacion) {
        return miembrosPorTrayecto.stream()
                .map(MiembroPorTrayecto::getMiembro)
                .filter(miembro -> miembro.getOrganizacion().equals(organizacion))
                .collect(Collectors.toList());
    }*/

    public int getCantidadDeMesesDeDuracion() {
        return DateTimeUtils.mesesEntreFechas(fechaInicio, fechaFin);
    }

    public Lugar getInicio() {
        return inicio;
    }

    public void setInicio(Lugar inicio) {
        this.inicio = inicio;
    }

    public Lugar getFin() {
        return fin;
    }

    public void setFin(Lugar fin) {
        this.fin = fin;
    }

    public List<MiembroPorTrayecto> getMiembrosPorTrayecto() {
        return miembrosPorTrayecto;
    }

    public void setMiembrosPorTrayecto(List<MiembroPorTrayecto> miembrosPorTrayecto) {
        this.miembrosPorTrayecto = miembrosPorTrayecto;
    }

    public List<Tramo> getTramos() {
        return tramos;
    }

    public void setTramos(List<Tramo> tramos) {
        this.tramos = tramos;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
