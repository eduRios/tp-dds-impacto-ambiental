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

    public Tramo() {
    }

    public Tramo(String desde, String hasta, MedioTransporte medioTransporte) {
        this.desde = desde;
        this.hasta = hasta;
        this.medioTransporte = medioTransporte;
    }
}
