package com.dds.tpimpactoambiental.dtos;


import com.dds.tpimpactoambiental.model.MiembroPorTrayecto;

public class MiembroPorTrayectoDto extends BaseEntityDto{
    private IdTextPair miembro;
    private IdTextPair trayecto;
    private double peso;

    public MiembroPorTrayectoDto() {
    }

    private MiembroPorTrayectoDto(MiembroPorTrayecto miembroPorTrayecto) {
        super(miembroPorTrayecto);
    }

    public static MiembroPorTrayectoDto from(MiembroPorTrayecto miembroPorTrayecto) {
        MiembroPorTrayectoDto dto = new MiembroPorTrayectoDto(miembroPorTrayecto);
        dto.setMiembro(new IdTextPair(miembroPorTrayecto.getMiembro()));
        dto.setTrayecto(new IdTextPair(miembroPorTrayecto.getTrayecto()));
        dto.setPeso(miembroPorTrayecto.getPeso());
        return dto;
    }

    public IdTextPair getMiembro() {
        return miembro;
    }

    public void setMiembro(IdTextPair miembro) {
        this.miembro = miembro;
    }

    public IdTextPair getTrayecto() {
        return trayecto;
    }

    public void setTrayecto(IdTextPair trayecto) {
        this.trayecto = trayecto;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
