package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.model.DatosActividad;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };
    static String SHEET = "sheet1";
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<DatosActividad> excelToTutorials(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(workbook.getActiveSheetIndex());
            Iterator<Row> rows = sheet.iterator();
            List<DatosActividad> tutorials = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber <= 1) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                DatosActividad actividad = new DatosActividad();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            actividad.setActividad(currentCell.getStringCellValue());
                            break;
                        case 1:
                            actividad.setTipoConsumo(currentCell.getStringCellValue());
                            break;
                        case 2:
                            actividad.setValor((long) currentCell.getNumericCellValue());
                            break;
                        case 3:
                            actividad.setPeriocidad(currentCell.getStringCellValue());
                            break;
                        case 4:
                            actividad.setPeriodoImputacion(currentCell.toString());
                            //LocalDateTime date = currentCell.getLocalDateTimeCellValue();
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                tutorials.add(actividad);
            }
            workbook.close();
            return tutorials;
        } catch ( IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static boolean cellIsEmpty(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK)
            return true;

        if (cell.getCellType() == CellType.STRING) {
            String cellValue = cell.getStringCellValue().trim();
            if (cellValue.isEmpty() || cellValue.equals("-"))
                return true;
        }

        return false;
    }

    public static String getCellStringValueOrNullIfEmpty(Cell cell) {
        if (cellIsEmpty(cell))
            return null;
        return cell.getStringCellValue().trim();
    }

    public static String readCellAsString(Cell cell) {
        DataFormatter dataFormatter = new DataFormatter();
        return dataFormatter.formatCellValue(cell);
    }
}

