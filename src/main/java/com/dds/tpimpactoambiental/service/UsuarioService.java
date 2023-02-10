package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.dtos.request.RequestRegistrarUsuario;
import com.dds.tpimpactoambiental.dtos.response.LoginResponse;
import com.dds.tpimpactoambiental.dtos.response.RegistrarUsuarioResponse;
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
    public RegistrarUsuarioResponse registrar(@RequestBody RequestRegistrarUsuario request) {
        Response response = new Response();
        if (existeUsuarioConUsername(request.getUsername())) {
            return new RegistrarUsuarioResponse(HttpStatus.FORBIDDEN,"Ya existe otro usuario con ese username");
        }
        try {
            validatePassword.validar(request.getPassword());
        } catch (PasswordException e) {
            return new RegistrarUsuarioResponse(HttpStatus.FORBIDDEN, e.getMessage());
        }

        Solicitud solicitud = solicitudRepository.getById(request.getIdSolicitud());
        if (solicitud == null) {
            return new RegistrarUsuarioResponse( HttpStatus.FORBIDDEN,"No se encontro la solicitud con el ID especificado");
        }

        Usuario usuario = crearUsuario(request.getUsername(), request.getPassword());
        userRepository.save(usuario);
        Miembro miembro = solicitud.getMiembro();
        miembro.setUsuario(usuario);
        miembroRepository.save(miembro);
        return new RegistrarUsuarioResponse(HttpStatus.CREATED,"Registro con exito");
    }

    public LoginResponse iniciarSesion(@RequestBody Usuario usuario) {

        Usuario user = userRepository.findByUsernameAndPassword(usuario.getUsername(), usuario.getPassword());
        if (intentos == 5) {
            intentos = 0;
            return new LoginResponse(HttpStatus.FORBIDDEN,"Has terminado tu limite de 5 intentos");
        }
        if (user == null) {
            intentos++;
            return new LoginResponse(HttpStatus.FORBIDDEN,"Usuario inexistente");
        }
        intentos = 0;
        LoginResponse response = new LoginResponse(HttpStatus.OK, "Guardar el `accessToken` y mandarlo en cada request");
        return response;
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