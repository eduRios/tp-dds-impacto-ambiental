package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.dtos.request.RequestRegistrarUsuario;
import com.dds.tpimpactoambiental.dtos.response.LoginResponse;
import com.dds.tpimpactoambiental.dtos.response.RegistrarUsuarioResponse;
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
import com.dds.tpimpactoambiental.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    UsuarioService usuarioService;
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<RegistrarUsuarioResponse> registrar(@RequestBody RequestRegistrarUsuario request){
        return ResponseEntityUtils.toResponseEntity(usuarioService.registrar(request));
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> iniciarSesion(@RequestBody Usuario usuario){
        return ResponseEntityUtils.toResponseEntity(usuarioService.iniciarSesion(usuario));
    }
}
