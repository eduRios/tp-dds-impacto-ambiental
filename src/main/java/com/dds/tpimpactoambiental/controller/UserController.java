package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.model.Usuario;
import com.dds.tpimpactoambiental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/registrar", method = RequestMethod.POST)
    public ResponseEntity<Object> registrar(@RequestBody Usuario usuario){
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
        if (user ==  null) {
            return new ResponseEntity<>("Usuario inexistente", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
