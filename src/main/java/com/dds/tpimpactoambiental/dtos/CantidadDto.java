package com.dds.tpimpactoambiental.dtos;

import com.dds.tpimpactoambiental.model.Cantidad;

public class CantidadDto extends BaseEntityDto {
    private IdTextPair unidad;
    private double valor;
    private String text;

    public CantidadDto() {
    }

    private CantidadDto(Cantidad cantidad) {
        super(cantidad);
    }

    public static CantidadDto from(Cantidad cantidad) {
        CantidadDto dto = new CantidadDto(cantidad);
        if (cantidad.tieneUnidad()) {
            dto.setUnidad(new IdTextPair(cantidad.getUnidad()));
        }
        dto.setValor(cantidad.getValor());
        dto.setText();
        return dto;
    }

    public IdTextPair getUnidad() {
        return unidad;
    }

    public void setUnidad(IdTextPair unidad) {
        this.unidad = unidad;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private void setText() {
        this.text = this.unidad != null
                ? this.valor + " " + this.unidad.getText()
                : String.valueOf(this.valor);
    }
}
