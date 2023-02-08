package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.dtos.request.RequestRegistrarUsuario;
import com.dds.tpimpactoambiental.dtos.response.LoginResponse;
import com.dds.tpimpactoambiental.dtos.response.RegistrarUsuarioResponse;
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
        RequestRegistrarUsuario user = new RequestRegistrarUsuario("raul77","raul923681",0);
        ResponseEntity<RegistrarUsuarioResponse> response = userController.registrar(user);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }
    @Test
    public void registrarErrorContraseñaDebil(){
        RequestRegistrarUsuario user = new RequestRegistrarUsuario("raul77","12345678");
        ResponseEntity<RegistrarUsuarioResponse> response = userController.registrar(user);
        assertEquals(HttpStatus.FORBIDDEN,response.getStatusCode());
        assertEquals("La Password es debil",response.getBody().getMessage());
    }

    @Test
    public void registrarErrorLimiteInsuficiente(){
        RequestRegistrarUsuario user = new RequestRegistrarUsuario("raul77","1234");
        ResponseEntity<RegistrarUsuarioResponse> response = userController.registrar(user);
        assertEquals(HttpStatus.FORBIDDEN,response.getStatusCode());
        assertEquals("limite insuficiente",response.getBody().getMessage());
    }

    @Test
    public void registrarErrorTieneCaracteresRepetidos(){
        RequestRegistrarUsuario user = new RequestRegistrarUsuario("raul77","1234433333");
        ResponseEntity<RegistrarUsuarioResponse> response = userController.registrar(user);
        assertEquals(HttpStatus.FORBIDDEN,response.getStatusCode());
        assertEquals("tiene caracteres repetidos",response.getBody().getMessage());
    }

    @Test
    public void iniciarSesionOk(){
        Usuario user = new Usuario("bari","wowesmivida12");
        ResponseEntity<LoginResponse> response = userController.iniciarSesion(user);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
    @Test
    public void iniciarSesionErrorUsuarioInexistente(){
        Usuario user = new Usuario("raul77","raul923682");
        ResponseEntity<LoginResponse> response = userController.iniciarSesion(user);
        assertEquals(HttpStatus.FORBIDDEN,response.getStatusCode());
        assertEquals("Usuario inexistente",response.getBody().getMessage());
    }
}
