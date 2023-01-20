package com.dds.tpimpactoambiental.model;

import javax.persistence.*;

@Entity(name = "Parada")
@Table(name = "paradas")
public class Parada extends Lugar {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "transporte_publico", nullable = false, foreignKey = @ForeignKey(name = "FK_Paradas_TransportePublico"))
    private TransportePublico transportePublico;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "distancia_parada_siguiente", foreignKey = @ForeignKey(name = "FK_Paradas_DistanciaParadaSiguiente"))
    private Cantidad distanciaParadaSiguiente;

    @OneToOne
    @JoinColumn(
            name = "parada_siguiente",
            unique = true,
            foreignKey = @ForeignKey(name = "FK_Paradas_ParadaSiguiente")
    )
    private Parada paradaSiguiente;

    public Parada() {
    }

    public Parada(Direccion direccion, Cantidad distanciaParadaSiguiente) {
        super(direccion);
        this.distanciaParadaSiguiente = distanciaParadaSiguiente;
    }

    public TransportePublico getTransportePublico() {
        return transportePublico;
    }

    public void setTransportePublico(TransportePublico transportePublico) {
        this.transportePublico = transportePublico;
    }

    public Cantidad getDistanciaParadaSiguiente() {
        return distanciaParadaSiguiente;
    }

    public void setDistanciaParadaSiguiente(Cantidad distanciaParadaSiguiente) {
        this.distanciaParadaSiguiente = distanciaParadaSiguiente;
    }

    public Parada getParadaSiguiente() {
        return paradaSiguiente;
    }

    public void setParadaSiguiente(Parada paradaSiguiente) {
        this.paradaSiguiente = paradaSiguiente;
    }

    @Override
    public String toString() {
        return "Parada de " + transportePublico.toString() + " en " + direccion.toString();
    }
}
