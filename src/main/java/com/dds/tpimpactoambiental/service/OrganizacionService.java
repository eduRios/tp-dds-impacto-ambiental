package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.dtos.*;
import com.dds.tpimpactoambiental.dtos.request.RequestAceptarSolicitud;
import com.dds.tpimpactoambiental.dtos.request.RequestCargarMediciones;
import com.dds.tpimpactoambiental.dtos.request.RequestCrearOrganizacion;
import com.dds.tpimpactoambiental.dtos.response.Response;
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
import java.util.*;
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
    private RegistroCalculoHCDatoActividadService registroCalculoHCDatoActividadService;

    @Autowired
    private ExcelService excelService;

    @Autowired
    private CalculadoraHC calculadoraHC;

    @Autowired
    private SectorTerritorialService sectorTerritorialService;

    public OrganizacionService() {
    }

    @Transactional
    public ResponseEntity<Object> crearOrganizacion(OrganizacionDto request){
        ResponseCrearOrganizacion response = new ResponseCrearOrganizacion();
        Organizacion organizacion = new Organizacion();
        updateEntityFieldsFromDto(organizacion, request);
        organizationRepository.save(organizacion);
        SectorTerritorial sectorTerritorial = sectorTerritorialService.getById(request.getSectorTerritorial().getId());
        sectorTerritorial.addOrganizacion(organizacion);
        response.setMessage("Creacion existosa");
        response.setIdOrganizacion(organizacion.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseWithSingleResult<OrganizacionDto>  editarOrganizacion(long id, OrganizacionDto request){
        if (id != request.getId()) {
            return new ResponseWithSingleResult<>(
                    HttpStatus.BAD_REQUEST,
                    "El ID de la ruta (" + id + ")  no coincide con el de la request (" + request.getId() + ")",
                    null);
        }

        Organizacion organizacion = organizationRepository.findById(request.getId()).orElse(null);
        updateEntityFieldsFromDto(organizacion, request);
        organizationRepository.save(organizacion);
        return new ResponseWithSingleResult<>(HttpStatus.OK, createDtoFromEntity(organizacion));
    }

    public Response eliminarOrganizacion(long id){
        organizationRepository.deleteById(id);
        return new Response(HttpStatus.OK);
    }

    @Transactional
    public ResponseWithSingleResult<OrganizacionDto> getDtoById(long id) {
        return new ResponseWithSingleResult<>(HttpStatus.OK, createDtoFromEntity(organizationRepository.getById(id)));
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

        calcularHCDatosActividadYGuardarRegistroCalculo(organizacion, medicionesParseadas);
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

    public void updateContactoFromDto(Contacto contacto, ContactoDto dto) {
        contacto.setNombre(dto.getNombre());
        contacto.setApellido(dto.getApellido());
        contacto.setEmail(dto.getEmail());
        contacto.setTelefono(dto.getTelefono());
        contacto.setDeseaRecibirPorWhatsapp(dto.getDeseaRecibirPorWhatsapp());
        contacto.setDeseaRecibirPorMail(dto.getDeseaRecibirPorMail());
    }

    private void updateEntityFieldsFromDto(Organizacion organizacion, OrganizacionDto dto) {
        organizacion.setRazonSocial(dto.getRazonSocial());
        organizacion.setTipoOrganizacion(TipoOrganizacion.getFromOrdinal(dto.getTipoOrganizacion().getId()));
        organizacion.setClasificacion(Clasificacion.getFromOrdinal(dto.getClasificacion().getId()));
        organizacion.setCantDiasHabilesPorSemana(dto.getCantDiasHabilesPorSemana());

        Unidad unidadFactorK = dto.getFactorK().getUnidad() != null
                ? unidadService.getById(dto.getFactorK().getUnidad().getId())
                : null;
        Cantidad factorK = new Cantidad(unidadFactorK, dto.getFactorK().getValor());
        organizacion.setFactorK(factorK);

        // Si un Contacto esta guardado en la BD y no esta en el DTO, es que se elimino
        organizacion.getContactos().removeIf(contacto -> dto.getContactos().stream().noneMatch(contactoDto -> contacto.getId() == contactoDto.getId()));

        for (ContactoDto contactoDto : dto.getContactos()) {
            if (contactoDto.getId() != 0) {
                Contacto contacto = organizacion.getContactos().stream().filter(c -> c.getId() == contactoDto.getId()).findFirst().get();
                updateContactoFromDto(contacto, contactoDto);
            } else {
                Contacto contacto = new Contacto();
                updateContactoFromDto(contacto, contactoDto);
                organizacion.addContacto(contacto);
            }
        }
    }

    private void calcularHCDatosActividadYGuardarRegistroCalculo(Organizacion organizacion, List<Medicion> mediciones) {
        Map<LocalDate, Cantidad> huellasDeCarbonoMensuales = new HashMap<>();
        Map<LocalDate, Cantidad> huellasDeCarbonoAnuales = new HashMap<>();
        Set<Medicion> medicionesYaProcesadas = new HashSet<>();

        for (Medicion medicion : mediciones) {
            if (medicionesYaProcesadas.contains(medicion))
                continue;

            medicionesYaProcesadas.add(medicion);
            Cantidad valorHuellaDeCarbono;
            if (medicion.getActividad() == Actividad.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS) {
                List<Medicion> medicionesCorrespondientes = getMedicionesCorrespondientesDeLogistica(medicion, mediciones);
                valorHuellaDeCarbono = calculadoraHC.calcularHCDatoActividadLogistica(medicionesCorrespondientes);
                // Para que no se vuelvan a procesar las Mediciones que corresponden con la Medicion actual (se procesan todas juntas)
                medicionesYaProcesadas.addAll(medicionesCorrespondientes);
            } else {
                valorHuellaDeCarbono = calculadoraHC.calcularHCDatoActividadNoLogistica(medicion);
            }

            if (medicion.getPeriodicidad() == Periodicidad.MENSUAL) {
                huellasDeCarbonoMensuales.merge(medicion.getPeriodoImputacion(), valorHuellaDeCarbono,
                        Cantidad::add);
            } else {
                huellasDeCarbonoAnuales.merge(medicion.getPeriodoImputacion(), valorHuellaDeCarbono,
                        Cantidad::add);
            }
        }

        guardarRegistrosCalculoHCMensuales(organizacion, huellasDeCarbonoMensuales);
        guardarRegistrosCalculoHCAnuales(organizacion, huellasDeCarbonoAnuales);
    }

    private void guardarRegistrosCalculoHCMensuales(Organizacion organizacion, Map<LocalDate, Cantidad> huellasDeCarbono) {
        huellasDeCarbono.forEach((periodoImputacion, valorHC) -> {
            RegistroCalculoHCDatoActividad registroCalculoHC = new RegistroCalculoHCDatoActividad(
                    Periodicidad.MENSUAL, periodoImputacion, valorHC);
            organizacion.addRegistroCalculoHC(registroCalculoHC);
            registroCalculoHCDatoActividadService.save(registroCalculoHC);
        });
    }

    private void guardarRegistrosCalculoHCAnuales(Organizacion organizacion, Map<LocalDate, Cantidad> huellasDeCarbono) {
        huellasDeCarbono.forEach((periodoImputacion, valorHC) -> {
            valorHC = calculadoraHC.calcularHCAnualProrrateadoDatoActividad(valorHC, periodoImputacion.getYear());
            Optional<RegistroCalculoHCDatoActividad> optionalRegistroCalculoHC = registroCalculoHCDatoActividadService.getRegistroCalculoHCParaPeriodo(
                    organizacion.getId(), Periodicidad.ANUAL, periodoImputacion
            );
            if (optionalRegistroCalculoHC.isPresent()) {
                RegistroCalculoHCDatoActividad registroExistenteCalculoHC = optionalRegistroCalculoHC.get();
                registroExistenteCalculoHC.setValor(valorHC);
            } else {
                RegistroCalculoHCDatoActividad registroCalculoHC = new RegistroCalculoHCDatoActividad(
                        Periodicidad.ANUAL, periodoImputacion, valorHC);
                organizacion.addRegistroCalculoHC(registroCalculoHC);
                registroCalculoHCDatoActividadService.save(registroCalculoHC);
            }
        });
    }

    /**
     * Retorna todas las Mediciones que se corresponden con la Medicion que recibe por parametro (coinciden la Periodicidad
     * y el PeriodoImputacion).
     */
    private List<Medicion> getMedicionesCorrespondientesDeLogistica(Medicion medicionAComparar, List<Medicion> mediciones) {
        return mediciones.stream()
                .filter(medicion -> medicion.getActividad() == Actividad.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS &&
                        medicion.getPeriodicidad() == medicionAComparar.getPeriodicidad() &&
                        medicion.getPeriodoImputacion().equals(medicionAComparar.getPeriodoImputacion()))
                .collect(Collectors.toList());
    }

}
