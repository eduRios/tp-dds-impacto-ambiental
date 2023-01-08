package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.dtos.request.RequestRegistrarUsuario;
import com.dds.tpimpactoambiental.exception.PasswordException;
import com.dds.tpimpactoambiental.model.Miembro;
import com.dds.tpimpactoambiental.model.Rol;
import com.dds.tpimpactoambiental.model.Usuario;
import com.dds.tpimpactoambiental.repository.MemberRepository;
import com.dds.tpimpactoambiental.repository.RolRepository;
import com.dds.tpimpactoambiental.repository.UserRepository;
import com.dds.tpimpactoambiental.service.UsuarioService;
import com.dds.tpimpactoambiental.service.ValidatePassword;
import com.dds.tpimpactoambiental.service.ValidatePasswordImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UsuarioService usuarioService;
    @RequestMapping(path = "/registrar", method = RequestMethod.POST)
    public ResponseEntity<Object> registrar(@RequestBody RequestRegistrarUsuario request){
        return usuarioService.registrar(request);
    }

    @RequestMapping(path = "/iniciar", method = RequestMethod.POST)
    public ResponseEntity<Object> iniciarSesion(@RequestBody Usuario usuario){
        return usuarioService.iniciarSesion(usuario);
    }
}
