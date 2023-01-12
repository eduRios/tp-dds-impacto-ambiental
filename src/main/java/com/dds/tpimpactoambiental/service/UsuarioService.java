package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.dtos.request.RequestRegistrarUsuario;
import com.dds.tpimpactoambiental.dtos.response.Response;
import com.dds.tpimpactoambiental.exception.PasswordException;
import com.dds.tpimpactoambiental.model.Miembro;
import com.dds.tpimpactoambiental.model.Rol;
import com.dds.tpimpactoambiental.model.Usuario;
import com.dds.tpimpactoambiental.repository.MemberRepository;
import com.dds.tpimpactoambiental.repository.RolRepository;
import com.dds.tpimpactoambiental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
public class UsuarioService {

    private ValidatePassword validatePassword = new ValidatePasswordImpl();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private MemberRepository miembroRepository;
    private int intentos =0;

    public ResponseEntity<Object> registrar(@RequestBody RequestRegistrarUsuario request){
        Response response = new Response();
        if (existeUsuarioConUsername(request.getUsername())) {
            return new ResponseEntity<>(response.message("Ya existe otro usuario con ese username"), HttpStatus.FORBIDDEN);
        }
        try {
            validatePassword.validar(request.getPassword());
        }catch (PasswordException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }

        Miembro miembro = miembroRepository.getById(request.getIdMiembro());
        if (miembro == null) {
            return new ResponseEntity<>(response.message("No se encontro al Miembro con el ID especificado"),HttpStatus.FORBIDDEN);
        }

        Usuario usuario = new Usuario(request.getUsername(),request.getPassword());
        Rol rol = rolRepository.findByName("USER");
        usuario.setRoles(new ArrayList<>());
        usuario.getRoles().add(rol);
        userRepository.save(usuario);
        miembro.setUsuario(usuario);
        miembroRepository.save(miembro);
        return new ResponseEntity<>(response.message("Registro con exito"),HttpStatus.CREATED);
    }

    public ResponseEntity<Object> iniciarSesion(@RequestBody Usuario usuario){

        Usuario user = userRepository.findByUsernameAndPassword(usuario.getUsername(),usuario.getPassword());
        if (intentos == 5) {
            intentos=0;
            return new ResponseEntity<>("Has terminado tu limite de 5 intentos", HttpStatus.FORBIDDEN);
        }
        if (user ==  null) {
            intentos++;
            return new ResponseEntity<>("Usuario inexistente", HttpStatus.FORBIDDEN);
        }
        intentos=0;
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public boolean existeUsuarioConUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
