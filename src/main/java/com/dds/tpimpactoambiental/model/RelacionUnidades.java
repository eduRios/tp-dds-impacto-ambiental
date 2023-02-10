package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity(name = "RelacionUnidades")
@Table(name = "relaciones_unidades")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RelacionUnidades extends BaseEntity {

    @ManyToOne
    @JoinColumn(
            name = "unidad_izquierda",
            foreignKey = @ForeignKey(name = "FK_RelacionesUnidades_UnidadIzquierda")
    )
    private Unidad unidadIzquierda;

    @ManyToOne
    @JoinColumn(
            name = "unidad_derecha",
            foreignKey = @ForeignKey(name = "FK_RelacionesUnidades_UnidadDerecha")
    )
    private Unidad unidadDerecha;

    @ManyToOne
    @JoinColumn(
            name = "unidad_producto",
            foreignKey = @ForeignKey(name = "FK_RelacionesUnidades_UnidadProducto")
    )
    private Unidad unidadProducto;

    @ManyToOne
    @JoinColumn(
            name = "unidad_cociente",
            foreignKey = @ForeignKey(name = "FK_RelacionesUnidades_UnidadCociente")
    )
    private Unidad unidadCociente;

    public RelacionUnidades() {
    }

    public RelacionUnidades(Unidad unidadIzquierda, Unidad unidadDerecha, Unidad unidadProducto, Unidad unidadCociente) {
        this.unidadIzquierda = unidadIzquierda;
        this.unidadDerecha = unidadDerecha;
        this.unidadProducto = unidadProducto;
        this.unidadCociente = unidadCociente;
    }

    public Unidad getUnidadIzquierda() {
        return unidadIzquierda;
    }

    public void setUnidadIzquierda(Unidad unidadIzquierda) {
        this.unidadIzquierda = unidadIzquierda;
    }

    public Unidad getUnidadDerecha() {
        return unidadDerecha;
    }

    public void setUnidadDerecha(Unidad unidadDerecha) {
        this.unidadDerecha = unidadDerecha;
    }

    public Unidad getUnidadProducto() {
        return unidadProducto;
    }

    public void setUnidadProducto(Unidad unidadProducto) {
        this.unidadProducto = unidadProducto;
    }

    public Unidad getUnidadCociente() {
        return unidadCociente;
    }

    public void setUnidadCociente(Unidad unidadCociente) {
        this.unidadCociente = unidadCociente;
    }
}

