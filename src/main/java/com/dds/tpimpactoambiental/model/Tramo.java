package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tramo")
public class Tramo {



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



    public Tramo() {
    }

    public Long getId() {
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
}
