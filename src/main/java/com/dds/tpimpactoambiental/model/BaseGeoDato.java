package com.dds.tpimpactoambiental.model;


import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseGeoDato extends BaseEntity {
    private int idSegunApi; // El id que devuelve la API de geodds
    private String nombre;

    public BaseGeoDato(int idSegunApi, String nombre) {
        this.idSegunApi = idSegunApi;
        this.nombre = nombre;
    }

    public BaseGeoDato() {
    }

    @Override
    public String toString() {
        return nombre;
    }

    public int getIdSegunApi() {
        return idSegunApi;
    }

    public void setIdSegunApi(int idSegunApi) {
        this.idSegunApi = idSegunApi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
