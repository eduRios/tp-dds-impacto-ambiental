package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity(name = "RegistroCalculoHCTrayecto")
@Table(name = "registros_calculo_hc_trayecto")
@Immutable
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class RegistroCalculoHCTrayecto extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "trayecto", nullable = false,
            foreignKey = @ForeignKey(name = "FK_RegistrosCalculoHCTrayecto_Trayecto"))
    private Trayecto trayecto;

    @ManyToOne
    @JoinColumn(name = "miembro", nullable = false,
            foreignKey = @ForeignKey(name = "FK_RegistrosCalculoHCTrayecto_Miembro"))
    private Miembro miembro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "valor", nullable = false,
            foreignKey = @ForeignKey(name = "FK_RegistrosCalculoHCTrayecto_Valor"))
    private Cantidad valor;

    public RegistroCalculoHCTrayecto() {
    }

    public RegistroCalculoHCTrayecto(Miembro miembro, Cantidad valor) {
        this.miembro = miembro;
        this.valor = valor;
    }

    public Trayecto getTrayecto() {
        return trayecto;
    }

    public void setTrayecto(Trayecto trayecto) {
        this.trayecto = trayecto;
    }

    public Miembro getMiembro() {
        return miembro;
    }

    public void setMiembro(Miembro miembro) {
        this.miembro = miembro;
    }

    public Cantidad getValor() {
        return valor;
    }

    public void setValor(Cantidad valor) {
        this.valor = valor;
    }
}
