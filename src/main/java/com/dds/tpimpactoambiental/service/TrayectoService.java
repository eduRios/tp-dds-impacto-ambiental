package com.dds.tpimpactoambiental.service;


import com.dds.tpimpactoambiental.dtos.TramoDto;
import com.dds.tpimpactoambiental.dtos.TrayectoDto;
import com.dds.tpimpactoambiental.model.*;
import com.dds.tpimpactoambiental.repository.LugarRepository;
import com.dds.tpimpactoambiental.repository.MedioTransporteRepository;
import com.dds.tpimpactoambiental.repository.MemberRepository;
import com.dds.tpimpactoambiental.repository.TrayectoRepository;
import com.dds.tpimpactoambiental.utils.DateTimeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrayectoService {

    private final TrayectoRepository trayectoRepository;
    private final LugarRepository lugarRepository;
    private final MemberRepository miembroRepository;
    private final MedioTransporteRepository medioDeTransporteRepository;
    //private final CalculadoraDistancias calculadoraDistancias;
    //private final CalculadoraHC calculadoraHC;

    public TrayectoService(TrayectoRepository repository, TrayectoRepository trayectoRepository, LugarRepository lugarRepository, MemberRepository miembroRepository, MedioTransporteRepository medioDeTransporteRepository) {
        this.trayectoRepository = trayectoRepository;
        this.lugarRepository = lugarRepository;
        this.miembroRepository = miembroRepository;
        this.medioDeTransporteRepository = medioDeTransporteRepository;
        //this.calculadoraDistancias = calculadoraDistancias;
        //this.calculadoraHC = calculadoraHC;
    }

    @Transactional
    public void crearTrayecto(TrayectoDto trayectoDto) {

        Lugar lugarInicio = lugarRepository.getById(trayectoDto.getLugarInicio().getId());
        Lugar lugarFin = lugarRepository.getById(trayectoDto.getLugarFin().getId());

        LocalDate fechaInicio = DateTimeUtils.dateWithOnlyYearAndMonth(trayectoDto.getFechaInicio());
        LocalDate fechaFin = DateTimeUtils.dateWithOnlyYearAndMonth(trayectoDto.getFechaFin());

        Trayecto trayecto = new Trayecto(lugarInicio, lugarFin, fechaInicio, fechaFin);

        List<MiembroPorTrayecto> miembros = trayectoDto.getMiembrosPorTrayecto().stream()
                .map(miembroPorTrayectoDto -> new MiembroPorTrayecto(
                        miembroRepository.getById(miembroPorTrayectoDto.getMiembro().getId()),
                        trayecto,
                        miembroPorTrayectoDto.getPeso()
                ))
                .collect(Collectors.toList());
        trayecto.addMiembros(miembros);

        for (TramoDto tramoDto : trayectoDto.getTramos()) {
            Tramo tramo = crearTramo(trayecto, tramoDto);
            trayecto.addTramo(tramo);
        }
        trayectoRepository.save(trayecto);

        //calcularHCTrayectoYGuardarRegistroCalculo(trayecto);
    }

    @Transactional
    public ResponseEntity<Object> listarTrayectos() {
        List<TrayectoDto> dtos = trayectoRepository.getAll().stream()
                .map(TrayectoDto::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    private Tramo crearTramo(Trayecto trayecto, TramoDto tramoDto) {
        MedioTransporte medioDeTransporte = medioDeTransporteRepository.getById(tramoDto.getMedioDeTransporte().getId());
        Lugar lugarInicio = lugarRepository.getById(tramoDto.getLugarInicio().getId());
        Lugar lugarFin = lugarRepository.getById(tramoDto.getLugarFin().getId());
        List<Miembro> miembros = tramoDto.getMiembros().stream()
                .map(idTextPair -> miembroRepository.getById(idTextPair.getId()))
                .collect(Collectors.toList());

        Tramo tramo = new Tramo(trayecto, medioDeTransporte, lugarInicio, lugarFin);
        tramo.addMiembros(miembros);
        //tramo.calcularDistanciaRecorrida(calculadoraDistancias);
        return tramo;
    }
/*
    private void calcularHCTrayectoYGuardarRegistroCalculo(Trayecto trayecto) {
        for (MiembroPorTrayecto miembroPorTrayecto : trayecto.getMiembrosPorTrayecto()) {
            Miembro miembro = miembroPorTrayecto.getMiembro();
            Cantidad hcMensualTrayectoParaMiembro = calculadoraHC.calcularHCMensualTrayectoParaMiembro(trayecto, miembro);
            RegistroCalculoHCTrayecto registroCalculoHCTrayecto = new RegistroCalculoHCTrayecto(miembro, hcMensualTrayectoParaMiembro);
            trayecto.addRegistroCalculoHCTrayecto(registroCalculoHCTrayecto);
        }
    }*/
}
