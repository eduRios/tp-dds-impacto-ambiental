package com.dds.tpimpactoambiental.model;

import javax.persistence.*;

@Entity
@Table(name="usuario")
public class Usuario {

    public Usuario() {
    }


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
