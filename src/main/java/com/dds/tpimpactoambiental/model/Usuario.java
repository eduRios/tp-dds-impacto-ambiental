package com.dds.tpimpactoambiental.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="usuario")
public class Usuario {

    public Usuario() {
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    String username;
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_roles",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="role_id"))
    List<Rol> roles;

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

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
}
