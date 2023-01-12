package com.dds.tpimpactoambiental.dtos.request;

public class RequestRegistrarUsuario {

    private String username;
    private String password;
    private long idMiembro;

    public RequestRegistrarUsuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

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

    public long getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(long idMiembro) {
        this.idMiembro = idMiembro;
    }
}
