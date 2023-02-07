package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.dtos.request.RequestRegistrarUsuario;
import com.dds.tpimpactoambiental.dtos.response.Response;
import com.dds.tpimpactoambiental.exception.PasswordException;
import com.dds.tpimpactoambiental.model.Miembro;
import com.dds.tpimpactoambiental.model.Rol;
import com.dds.tpimpactoambiental.model.Solicitud;
import com.dds.tpimpactoambiental.model.Usuario;
import com.dds.tpimpactoambiental.repository.MemberRepository;
import com.dds.tpimpactoambiental.repository.RolRepository;
import com.dds.tpimpactoambiental.repository.SolicitudRepository;
import com.dds.tpimpactoambiental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
public class UsuarioService {

    private ValidatePassword validatePassword = new ValidatePasswordImpl();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    MemberRepository miembroRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private SolicitudRepository solicitudRepository;
    private int intentos = 0;

    @Transactional
    public ResponseEntity<Object> registrar(@RequestBody RequestRegistrarUsuario request) {
        Response response = new Response();
        if (existeUsuarioConUsername(request.getUsername())) {
            return new ResponseEntity<>(response.message("Ya existe otro usuario con ese username"), HttpStatus.FORBIDDEN);
        }
        try {
            validatePassword.validar(request.getPassword());
        } catch (PasswordException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }

        Solicitud solicitud = solicitudRepository.getById(request.getIdSolicitud());
        if (solicitud == null) {
            return new ResponseEntity<>(response.message("No se encontro la solicitud con el ID especificado"), HttpStatus.FORBIDDEN);
        }

        Usuario usuario = crearUsuario(request.getUsername(), request.getPassword());
        userRepository.save(usuario);
        Miembro miembro = solicitud.getMiembro();
        miembro.setUsuario(usuario);
        miembroRepository.save(miembro);
        return new ResponseEntity<>(response.message("Registro con exito"), HttpStatus.CREATED);
    }

    public ResponseEntity<Object> iniciarSesion(@RequestBody Usuario usuario) {

        Usuario user = userRepository.findByUsernameAndPassword(usuario.getUsername(), usuario.getPassword());
        if (intentos == 5) {
            intentos = 0;
            return new ResponseEntity<>("Has terminado tu limite de 5 intentos", HttpStatus.FORBIDDEN);
        }
        if (user == null) {
            intentos++;
            return new ResponseEntity<>("Usuario inexistente", HttpStatus.FORBIDDEN);
        }
        intentos = 0;
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public boolean existeUsuarioConUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public Usuario crearUsuario(String username, String password) {
        Usuario usuario = new Usuario(username, password);
        Rol rol = rolRepository.findByName("USER");
        usuario.setRoles(new ArrayList<>());
        usuario.getRoles().add(rol);
        return usuario;

    }
}