package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tramo")
public class Tramo extends BaseEntity{



    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    Long id;
    String desde;
    String hasta;
    @OneToOne
    MedioTransporte medioTransporte;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trayectoria_id")
    private Trayectoria trayectoria;


    @ManyToOne
    @JoinColumn(name = "trayecto", nullable = false, foreignKey = @ForeignKey(name = "FK_Tramos_Trayecto"))
    private Trayecto trayecto;

    @ManyToOne
    @JoinColumn(name = "medio_de_transporte", nullable = false,
            foreignKey = @ForeignKey(name = "FK_Tramos_MedioDeTransporte"))
    private MedioTransporte medioDeTransporte;

    @ManyToOne
    @JoinColumn(name = "lugar_inicio", nullable = false, foreignKey = @ForeignKey(name = "FK_Tramos_LugarInicio"))
    private Lugar lugarInicio;

    @ManyToOne
    @JoinColumn(name = "lugar_fin", nullable = false, foreignKey = @ForeignKey(name = "FK_Tramos_LugarFin"))
    private Lugar lugarFin;

    @ManyToMany
    @JoinTable(
            name = "miembros_por_tramo",
            joinColumns = @JoinColumn(name = "tramo"),
            inverseJoinColumns = @JoinColumn(name = "miembro"),
            foreignKey = @ForeignKey(name = "FK_MiembrosPorTramo_Tramo"),
            inverseForeignKey = @ForeignKey(name = "FK_MiembrosPorTramo_Miembro")
    )
    private List<Miembro> miembros = new ArrayList<>();


    public Tramo() {
    }


    public long getId() {
        return id;
    }
    public String getHasta() {
        return hasta;
    }
    public String getDesde() {
        return desde;
    }
    public void setDesde(String desde) {
        this.desde = desde;
    }
    public Trayectoria getTrayectoria() {
        return trayectoria;
    }

    public void setTrayectoria(Trayectoria trayectoria) {
        this.trayectoria = trayectoria;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public void setMedioTransporte(MedioTransporte medioTransporte) {
        this.medioTransporte = medioTransporte;
    }
    public Tramo(String desde, String hasta, MedioTransporte medioTransporte) {
        this.desde = desde;
        this.hasta = hasta;
        this.medioTransporte = medioTransporte;
    }
    /*
        // Desnormaliza el dato de la distancia recorrida en el tramo, una vez que se calcula no tiene sentido volver
        // a calcularlo asi que se guarda aca.
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "distancia_recorrida", foreignKey = @ForeignKey(name = "FK_Tramos_DistanciaRecorrida"))
        private Cantidad distanciaRecorrida;
    */
    public Tramo(Trayecto trayecto, MedioTransporte medioDeTransporte, Lugar lugarInicio, Lugar lugarFin) {
        this.trayecto = trayecto;
        this.medioDeTransporte = medioDeTransporte;
        this.lugarInicio = lugarInicio;
        this.lugarFin = lugarFin;
    }

    public void addMiembro(Miembro miembro) {
        miembros.add(miembro);
        miembro.addTramo(this);
    }

    public void addMiembros(List<Miembro> miembros) {
        miembros.forEach(this::addMiembro);
    }

    public Trayecto getTrayecto() {
        return trayecto;
    }

    public void setTrayecto(Trayecto trayecto) {
        this.trayecto = trayecto;
    }

    public MedioTransporte getMedioDeTransporte() {
        return medioDeTransporte;
    }

    public void setMedioDeTransporte(MedioTransporte medioDeTransporte) {
        this.medioDeTransporte = medioDeTransporte;
    }

    public Lugar getLugarInicio() {
        return lugarInicio;
    }

    public void setLugarInicio(Lugar lugarInicio) {
        this.lugarInicio = lugarInicio;
    }

    public Lugar getLugarFin() {
        return lugarFin;
    }

    public void setLugarFin(Lugar lugarFin) {
        this.lugarFin = lugarFin;
    }

    public List<Miembro> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<Miembro> miembros) {
        this.miembros = miembros;

    }
}
