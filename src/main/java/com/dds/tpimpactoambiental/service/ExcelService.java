package com.dds.tpimpactoambiental.service;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.dds.tpimpactoambiental.model.DatosActividad;
import com.dds.tpimpactoambiental.model.Organizacion;

import com.dds.tpimpactoambiental.repository.DatosActividadRepository;
import com.dds.tpimpactoambiental.repository.OrganizationRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelService {

    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    DatosActividadRepository repository;
    public void save(MultipartFile file, Long idOrg) {
        try {
            Optional<Organizacion> organizacion = organizationRepository.findById(idOrg);
            List<DatosActividad> tutorials = repository.saveAll(ExcelHelper.excelToTutorials(file.getInputStream()));
            Organizacion o = organizacion.get();
            o.setDatosActividadList(tutorials);
            Organizacion oS = organizationRepository.save(o);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public <T> List<T> loadData(InputStream inputStream, String sheetName, int titleRowsQty, Function<Row, T> rowMapper) throws IOException {
        List<T> mappedRows = new ArrayList<>();

        try (Workbook wb = WorkbookFactory.create(inputStream)) {
            Sheet sheet = wb.getSheetAt(wb.getActiveSheetIndex());
            Iterator<Row> rowIterator = sheet.rowIterator();

            // Me salteo las filas que corresponden a las cabeceras del Excel
            for (int i = 1; i <= titleRowsQty; i++) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (ExcelHelper.cellIsEmpty(row.getCell(1))) {
                    break;
                }

                T mappedRow = rowMapper.apply(row);
                mappedRows.add(mappedRow);
                //log.debug("Row leida: " + mappedRow.toString());
            }
        }

        return mappedRows;
    }

    public List<DatosActividad> getAllTutorials() {
        return repository.findAll();
    }
}
