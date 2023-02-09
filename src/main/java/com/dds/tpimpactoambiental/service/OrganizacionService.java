package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.dtos.*;
import com.dds.tpimpactoambiental.dtos.request.RequestAceptarSolicitud;
import com.dds.tpimpactoambiental.dtos.request.RequestCargarMediciones;
import com.dds.tpimpactoambiental.dtos.request.RequestCrearOrganizacion;
import com.dds.tpimpactoambiental.dtos.response.ResponseAceptarSolicitud;
import com.dds.tpimpactoambiental.dtos.response.ResponseCrearOrganizacion;
import com.dds.tpimpactoambiental.enums.Actividad;
import com.dds.tpimpactoambiental.enums.Clasificacion;
import com.dds.tpimpactoambiental.enums.Periodicidad;
import com.dds.tpimpactoambiental.enums.TipoOrganizacion;
import com.dds.tpimpactoambiental.model.*;
import com.dds.tpimpactoambiental.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizacionService {
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private SolicitudRepository solicitudRepository;
    @Autowired
    private LocalidadRepository localidadRepository;
    @Autowired
    private UnidadRepository unidadService;
    @Autowired
    private TipoConsumoService tipoConsumoService;
    @Autowired
    private TipoMedioDeTransporteRepository tipoMedioDeTransporteRepository;

    @Autowired
    ExcelService excelService;

    public OrganizacionService() {
    }

    public ResponseEntity<Object> crearOrganizacion(RequestCrearOrganizacion request){
        ResponseCrearOrganizacion response = new ResponseCrearOrganizacion();
        Organizacion organizacion = new Organizacion();
        organizacion.setRazonSocial(request.getRazonSocial());
        organizacion.setTipoOrganizacion(TipoOrganizacion.getFromOrdinal(request.getTipoOrganizacion().getId()));
        organizacion.setClasificacion(Clasificacion.getFromOrdinal(request.getClasificacion().getId()));
        organizacion.setCantDiasHabilesPorSemana(request.getCantDiasHabilesPorSemana());
        organizacion.setCantDiasHabilesPorSemana(request.getCantDiasHabilesPorSemana());

        Unidad unidadFactorK = request.getFactorK().getUnidad() != null
                ? unidadService.getById(request.getFactorK().getUnidad().getId())
                : null;
        Cantidad factorK = new Cantidad(unidadFactorK, request.getFactorK().getValor());
        organizacion.setFactorK(factorK);
        organizationRepository.save(organizacion);
        response.setMessage("Creacion existosa");
        response.setIdOrganizacion(organizacion.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseWithResults<OrganizacionDto> getAllDtos() {
        List<OrganizacionDto> dtos = organizationRepository.findAll().stream()
                .map(this::createDtoFromEntity)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, dtos);
    }

    private OrganizacionDto createDtoFromEntity(Organizacion organizacion) {
        return OrganizacionDto.from(organizacion);
    }

    @Transactional
    public ResponseEntity<Object> aceptarSolicitud(RequestAceptarSolicitud request){
        ResponseAceptarSolicitud response = new ResponseAceptarSolicitud();
        Solicitud solicitud = solicitudRepository.findById(request.getIdSolicitud()).orElse(null);
        if (solicitud == null) {
            response.setMessage("No existe ninguna Solicitud con el ID ingresado");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
        }
        solicitud.getSector().aceptarSolicitud(solicitud);
        solicitud.getMiembro().setFechaIngreso(LocalDate.now());
        response.setMessage("Solicitud aceptada");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Transactional
    public ResponseWithResults<IdTextPair> listarTiposDeOrganizacion() {
        List<IdTextPair> tiposDeOrganizacion = Arrays.stream(TipoOrganizacion.values())
                .map(tipo -> new IdTextPair(tipo.ordinal(), tipo.toString()))
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, tiposDeOrganizacion);
    }

    @Transactional
    public ResponseWithResults<IdTextPair> listarClasificaciones() {
        List<IdTextPair> clasificaciones = Arrays.stream(Clasificacion.values())
                .map(clasificacion -> new IdTextPair(clasificacion.ordinal(), clasificacion.toString()))
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, clasificaciones);
    }

    @Transactional
    public ResponseWithResults<SolicitudDto> listarSolicitudes(long idOrganizacion) {
        List<SolicitudDto> solicitudes = solicitudRepository.solicitudesParaOrganizacion(idOrganizacion).stream()
                .map(SolicitudDto::from)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, solicitudes);
    }

    @Transactional
    public ResponseWithResults<MiembroDto> listarMiembros(long idOrganizacion) {
        Organizacion organizacion = organizationRepository.getById(idOrganizacion);
        List<MiembroDto> miembrosDtos = organizacion.getMiembros().stream()
                .map(MiembroDto::from)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, miembrosDtos);
    }

    @Transactional
    public void cargarMediciones(RequestCargarMediciones request) throws IOException {
        Organizacion organizacion = organizationRepository.getById(request.getIdOrganizacion());
        InputStream inputStream = request.getFile().getInputStream();
        List<RowMedicionActividad> mediciones = excelService.loadData(inputStream, "Hoja1", 2, RowMedicionActividad::fromRow);

        List<Medicion> medicionesParseadas = mediciones.stream()
                .map(this::rowToMedicion)
                .collect(Collectors.toList());
        organizacion.addMediciones(medicionesParseadas);

        //calcularHCDatosActividadYGuardarRegistroCalculo(organizacion, medicionesParseadas);
    }

    private Medicion rowToMedicion(RowMedicionActividad row) {
        Actividad actividad = Actividad.from(row.getActividad());
        TipoConsumo tipoConsumo = tipoConsumoService.getByNombre(row.getTipoDeConsumo())
                .orElseThrow(() -> new IllegalArgumentException("No existe un TipoConsumo con el nombre '" + row.getTipoDeConsumo() + "'"));
        Periodicidad periodicidad = Periodicidad.from(row.getPeriodicidad());
        Medicion medicion = new Medicion(actividad, tipoConsumo, periodicidad, row.getPeriodoImputacion(), row.getValor());

        if (medicion.getTipoConsumo().getNombre().equals("Medio de transporte")) {
            String nombreTipoMedioDeTransporte = medicion.getValorString();
            TipoMedioDeTransporte tipoMedioDeTransporte = tipoMedioDeTransporteRepository.getByNombre(nombreTipoMedioDeTransporte);
            medicion.setTipoMedioDeTransporte(tipoMedioDeTransporte);
        }

        return medicion;
    }

    @Transactional
    public void seedData() {

        if (organizationRepository.hasData()) {
            //log.debug("Seed: ya hay Organizaciones creadas");
            return;
        }

        Organizacion organizacion = new Organizacion(
                "Organizacion TEST",
                TipoOrganizacion.EMPRESA,
                Clasificacion.EMPRESA_SECTOR_PRIMARIO,
                new Cantidad(unidadService.getBySimbolo("1/kg").get(), 2),
                5
        );
        Direccion direccionEspacio = new Direccion("MEDRANO", "951");
        Localidad localidad = localidadRepository.getByNombre("ALMAGRO");
        localidad.addDireccion(direccionEspacio);
        Espacio espacio = new Espacio(direccionEspacio, "Espacio TEST", TipoEspacio.TRABAJO);
            Sector sector = new Sector("Sector TEST", organizacion, espacio);
        organizacion.addSector(sector);
        organizationRepository.saveAll(Arrays.asList(organizacion));
    }

}
