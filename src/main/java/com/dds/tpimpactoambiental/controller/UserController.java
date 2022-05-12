package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.exception.PasswordException;
import com.dds.tpimpactoambiental.model.Usuario;
import com.dds.tpimpactoambiental.repository.UserRepository;
import com.dds.tpimpactoambiental.service.ValidatePassword;
import com.dds.tpimpactoambiental.service.ValidatePasswordImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private ValidatePassword validatePassword = new ValidatePasswordImpl();
    @Autowired
    private UserRepository userRepository;
    private int intentos =0;

    @RequestMapping(path = "/registrar", method = RequestMethod.POST)
    public ResponseEntity<Object> registrar(@RequestBody Usuario usuario){
        try {
            validatePassword.validar(usuario.getPassword());
        }catch (PasswordException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
/*
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);*/
        userRepository.save(usuario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/iniciar", method = RequestMethod.POST)
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
}
