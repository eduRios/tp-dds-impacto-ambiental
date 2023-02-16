package com.dds.tpimpactoambiental.dtos;


import com.dds.tpimpactoambiental.service.ExcelHelper;
import org.apache.poi.ss.usermodel.Row;

public class RowRelacionUnidades {
    private final String unidadIzquierda;
    private final String unidadDerecha;
    private final String unidadProducto;
    private final String unidadCociente;

    private RowRelacionUnidades(String unidadIzquierda, String unidadDerecha, String unidadProducto, String unidadCociente) {
        this.unidadIzquierda = unidadIzquierda;
        this.unidadDerecha = unidadDerecha;
        this.unidadProducto = unidadProducto;
        this.unidadCociente = unidadCociente;
    }

    public String getUnidadIzquierda() {
        return unidadIzquierda;
    }

    public String getUnidadDerecha() {
        return unidadDerecha;
    }

    public String getUnidadProducto() {
        return unidadProducto;
    }

    public String getUnidadCociente() {
        return unidadCociente;
    }

    public static RowRelacionUnidades fromRow(Row row) {
        String unidadIzquierda = ExcelHelper.getCellStringValueOrNullIfEmpty(row.getCell(0));
        String unidadDerecha = ExcelHelper.getCellStringValueOrNullIfEmpty(row.getCell(1));
        String unidadProducto = ExcelHelper.getCellStringValueOrNullIfEmpty(row.getCell(2));
        String unidadCociente = ExcelHelper.getCellStringValueOrNullIfEmpty(row.getCell(3));
        return new RowRelacionUnidades(unidadIzquierda, unidadDerecha, unidadProducto, unidadCociente);
    }

    @Override
    public String toString() {
        return "RowRelacionUnidades{" +
                "unidadIzquierda='" + unidadIzquierda + '\'' +
                ", unidadDerecha='" + unidadDerecha + '\'' +
                ", unidadProducto='" + unidadProducto + '\'' +
                ", unidadCociente='" + unidadCociente + '\'' +
                '}';
    }
}
