package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<Usuario, Long> {
    Usuario findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}

