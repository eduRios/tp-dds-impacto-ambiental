package com.dds.tpimpactoambiental.dtos;


import com.dds.tpimpactoambiental.service.ExcelHelper;
import org.apache.poi.ss.usermodel.Row;

public class RowMedicionActividad {
    private final String actividad;
    private final String tipoDeConsumo;
    private final String valor;
    private final String periodicidad;
    private final String periodoImputacion;

    private RowMedicionActividad(String actividad, String tipoDeConsumo, String valor, String periodicidad,
                                 String periodoImputacion) {
        this.actividad = actividad;
        this.tipoDeConsumo = tipoDeConsumo;
        this.valor = valor;
        this.periodicidad = periodicidad;
        this.periodoImputacion = periodoImputacion;
    }

    public static RowMedicionActividad fromRow(Row row) {
        String actividad = ExcelHelper.readCellAsString(row.getCell(0));
        String tipoDeConsumo = ExcelHelper.readCellAsString(row.getCell(1));
        String valor = ExcelHelper.readCellAsString(row.getCell(2));
        String periodicidad = ExcelHelper.readCellAsString(row.getCell(3));
        String periodoImputacion = ExcelHelper.readCellAsString(row.getCell(4));
        return new RowMedicionActividad(actividad, tipoDeConsumo, valor, periodicidad, periodoImputacion);
    }

    public String getActividad() {
        return actividad;
    }

    public String getTipoDeConsumo() {
        return tipoDeConsumo;
    }

    public String getValor() {
        return valor;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public String getPeriodoImputacion() {
        return periodoImputacion;
    }

    @Override
    public String toString() {
        return "RowMedicionActividad{" +
                "actividad='" + actividad + '\'' +
                ", tipoDeConsumo='" + tipoDeConsumo + '\'' +
                ", valor='" + valor + '\'' +
                ", periodicidad='" + periodicidad + '\'' +
                ", periodoImputacion='" + periodoImputacion + '\'' +
                '}';
    }
}
