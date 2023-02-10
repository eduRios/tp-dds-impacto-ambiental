package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.model.Miembro;
import com.dds.tpimpactoambiental.model.Organizacion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MemberControllerTest {

    @Autowired
    private MemberController memberController;
/*
    @Test
    public void registrarDatosPersonalesOk(){
        Miembro max = new Miembro("Max","Power","DNI","22222222");
        ResponseEntity<Object> response = memberController.registrarDatosPersonales(max);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }

    @Test
    public void registrarOrganizacionOk(){
        Organizacion utn = new Organizacion("UTN","Institución", Arrays.asList("Centro de estudiantes","Direccion"),"Universidad");
        ResponseEntity<Object> response = memberController.registrarOrganizacion(3L,utn);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }*/
}
