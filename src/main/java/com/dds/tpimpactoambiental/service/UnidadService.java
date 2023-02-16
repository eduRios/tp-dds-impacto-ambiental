package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.dtos.ResponseWithResults;
import com.dds.tpimpactoambiental.dtos.RowRelacionUnidades;
import com.dds.tpimpactoambiental.dtos.UnidadDto;
import com.dds.tpimpactoambiental.model.Cantidad;
import com.dds.tpimpactoambiental.model.RelacionUnidades;
import com.dds.tpimpactoambiental.model.TipoUnidad;
import com.dds.tpimpactoambiental.model.Unidad;
import com.dds.tpimpactoambiental.repository.RelacionUnidadesRepository;
import com.dds.tpimpactoambiental.repository.TipoUnidadRepository;
import com.dds.tpimpactoambiental.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UnidadService {

    @Autowired
    private UnidadRepository repository;
    @Autowired
    private TipoUnidadRepository tipoUnidadRepository;

    @Autowired
    private ExcelService excelService;

    @Autowired
    private RelacionUnidadesRepository relacionUnidadesRepository;
    @Transactional
    public Optional<Unidad> getBySimbolo(String simbolo) {
        return repository.getBySimbolo(simbolo);
    }

    @Transactional
    public ResponseWithResults<UnidadDto> listarUnidades() {
        List<UnidadDto> dtos = repository.findAll().stream()
                .map(UnidadDto::from)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, dtos);
    }

    @Transactional
    public Cantidad sumarCantidades(List<Cantidad> cantidades, String unidadPorDefecto) {
        return cantidades.stream()
                .reduce(Cantidad::add)
                .orElse(new Cantidad(this.getBySimbolo(unidadPorDefecto).get(), 0));
    }

    @Transactional
    public void seedData() {
        boolean hasData = repository.hasData();
        if (hasData) {
            //log.debug("Seed: ya hay Unidades creadas");
            return;
        }

        seedUnidades();
        seedRelacionesUnidades();
    }

    private void seedUnidades() {
        //log.debug("Seed: se crean las Unidades iniciales");

        TipoUnidad longitud = new TipoUnidad("Longitud");
        Unidad KM = new Unidad("km", "Kilometro", true, 1d);
        Unidad M = new Unidad("m", "Metro", false, 1d / 1000);
        longitud.addUnidades(Arrays.asList(KM, M));

        TipoUnidad masa = new TipoUnidad("Masa");
        Unidad KG = new Unidad("kg", "Kilogramo", true, 1d);
        Unidad G = new Unidad("g", "Gramo", false, 1d / 1000);
        masa.addUnidades(Arrays.asList(KG, G));

        TipoUnidad volumen = new TipoUnidad("Volumen");
        Unidad M3 = new Unidad("m3", "Metro cubico", true, 1d);
        Unidad LT = new Unidad("lt", "Litro", false, 1d / 1000);
        volumen.addUnidades(Arrays.asList(M3, LT));

        TipoUnidad energia = new TipoUnidad("Energía");
        Unidad KWH = new Unidad("kWh", "Kilowatt-Hora", true, 1d);
        energia.addUnidad(KWH);

        TipoUnidad equivalenteCarbono = new TipoUnidad("Equivalente carbono");
        Unidad GCO2eq = new Unidad("gCO2eq", "Gramo equivalente carbono", true, 1d);
        Unidad KGCO2eq = new Unidad("kgCO2eq", "Kilogramo equivalente carbono", false, 1000d);
        Unidad TNCO2eq = new Unidad("tnCO2eq", "Tonelada equivalente carbono", false, 1_000_000d);
        equivalenteCarbono.addUnidades(Arrays.asList(GCO2eq, KGCO2eq, TNCO2eq));

        TipoUnidad unidadesCompuestas = new TipoUnidad("Unidades compuestas");
        Unidad GCO2eq_SOBRE_KM = new Unidad("gCO2eq/km", "Gramo equivalente carbono sobre Kilometro", true, 1);
        Unidad UNO_SOBRE_KG = new Unidad("1/kg", "1 sobre Kilogramo", true, 1);
        Unidad GCO2eq_SOBRE_M3 = new Unidad("gCO2eq/m3", "Gramo equivalente carbono sobre Metro cubico", true, 1);
        Unidad GCO2eq_SOBRE_LT = new Unidad("gCO2eq/lt", "Gramo equivalente carbono sobre Litro", true, 1);
        Unidad GCO2eq_SOBRE_KG = new Unidad("gCO2eq/kg", "Gramo equivalente carbono sobre Kilogramo", true, 1);
        Unidad GCO2eq_SOBRE_KWH = new Unidad("gCO2eq/kWh", "Gramo equivalente carbono sobre Kilowatt-Hora", true, 1);
        unidadesCompuestas.addUnidades(Arrays.asList(GCO2eq_SOBRE_KM, UNO_SOBRE_KG, GCO2eq_SOBRE_M3,
                GCO2eq_SOBRE_LT, GCO2eq_SOBRE_KG, GCO2eq_SOBRE_KWH));

        Iterable<TipoUnidad> iterable = Arrays.asList(longitud, masa, volumen, energia, equivalenteCarbono, unidadesCompuestas);
        tipoUnidadRepository.saveAll(iterable);
    }

    private void seedRelacionesUnidades() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("static/RelacionesUnidadesIniciales.xlsx");
            List<RowRelacionUnidades> rows = excelService.loadData(inputStream, "Hoja1", 1, RowRelacionUnidades::fromRow);
            seedRelacionesUnidades(rows);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void seedRelacionesUnidades(List<RowRelacionUnidades> rows) {
        for (RowRelacionUnidades row : rows) {
            String simboloUnidadIzquierda = row.getUnidadIzquierda();
            String simboloUnidadDerecha = row.getUnidadDerecha();
            String simboloUnidadProducto = row.getUnidadProducto();
            String simboloUnidadCociente = row.getUnidadCociente();

            Function<String, Unidad> functionGetUnidadBySimbolo = (String simbolo) -> {
                if (simbolo == null)
                    return null;
                return getBySimbolo(simbolo).orElseThrow(() -> new IllegalStateException("No existe la unidad " + simbolo));
            };

            Unidad unidadIzquierda = functionGetUnidadBySimbolo.apply(simboloUnidadIzquierda);
            Unidad unidadDerecha = functionGetUnidadBySimbolo.apply(simboloUnidadDerecha);
            Unidad unidadProducto = functionGetUnidadBySimbolo.apply(simboloUnidadProducto);
            Unidad unidadCociente = functionGetUnidadBySimbolo.apply(simboloUnidadCociente);

            RelacionUnidades relacion = new RelacionUnidades(unidadIzquierda, unidadDerecha, unidadProducto, unidadCociente);
            if (unidadIzquierda != null)
                unidadIzquierda.addRelacionEnIzquierda(relacion);
            if (unidadDerecha != null)
                unidadDerecha.addRelacionEnDerecha(relacion);
            if (unidadProducto != null)
                unidadProducto.addRelacionEnProducto(relacion);
            if (unidadCociente != null)
                unidadCociente.addRelacionEnCociente(relacion);
            relacionUnidadesRepository.save(relacion);
            saveAll(Arrays.asList(unidadIzquierda, unidadDerecha, unidadProducto, unidadCociente));
        }
    }

    public List<Unidad> saveAll(List<Unidad> entities) {
        return entities.stream()
                .filter(Objects::nonNull)
                .map(repository::save)
                .collect(Collectors.toList());
    }
}
