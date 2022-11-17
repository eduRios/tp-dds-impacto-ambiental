package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="trayectoria")
public class Trayectoria {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @OneToMany(mappedBy="trayectoria", fetch= FetchType.EAGER)
    List<Tramo> tramos;

    public Trayectoria(){

    }


    public Long getId() {
        return id;
    }
    public List<Tramo> getTramos() {
        return tramos;
    }

    public void setTramos(List<Tramo> tramos) {
        this.tramos = tramos;
    }

}
