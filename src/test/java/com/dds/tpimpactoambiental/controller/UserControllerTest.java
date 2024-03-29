package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void registrarOk(){
        Usuario user = new Usuario("raul77","raul923681");
        ResponseEntity<Object> response = userController.registrar(user);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }
    @Test
    public void registrarErrorContraseñaDebil(){
        Usuario user = new Usuario("raul77","12345678");
        ResponseEntity<Object> response = userController.registrar(user);
        assertEquals(HttpStatus.FORBIDDEN,response.getStatusCode());
        assertEquals("La Password es debil",response.getBody());
    }

    @Test
    public void registrarErrorLimiteInsuficiente(){
        Usuario user = new Usuario("raul77","1234");
        ResponseEntity<Object> response = userController.registrar(user);
        assertEquals(HttpStatus.FORBIDDEN,response.getStatusCode());
        assertEquals("limite insuficiente",response.getBody());
    }

    @Test
    public void registrarErrorTieneCaracteresRepetidos(){
        Usuario user = new Usuario("raul77","1234433333");
        ResponseEntity<Object> response = userController.registrar(user);
        assertEquals(HttpStatus.FORBIDDEN,response.getStatusCode());
        assertEquals("tiene caracteres repetidos",response.getBody());
    }

    @Test
    public void iniciarSesionOk(){
        Usuario user = new Usuario("bari","wowesmivida12");
        ResponseEntity<Object> response = userController.iniciarSesion(user);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
    @Test
    public void iniciarSesionErrorUsuarioInexistente(){
        Usuario user = new Usuario("raul77","raul923682");
        ResponseEntity<Object> response = userController.iniciarSesion(user);
        assertEquals(HttpStatus.FORBIDDEN,response.getStatusCode());
        assertEquals("Usuario inexistente",response.getBody());
    }
}
