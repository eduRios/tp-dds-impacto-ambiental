package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Usuario, Long> {
    Usuario findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    Optional<Usuario> findByUsername(@Param("username") String username);
}

