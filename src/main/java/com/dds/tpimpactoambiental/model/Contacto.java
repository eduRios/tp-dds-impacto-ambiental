package com.dds.tpimpactoambiental.model;

import javax.persistence.*;

@Entity(name = "Contacto")
@Table(name = "contactos")
public class Contacto extends BaseEntity {

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Boolean deseaRecibirPorWhatsapp;
    private Boolean deseaRecibirPorMail;

    @ManyToOne
    @JoinColumn(name = "organizacion", nullable = false, foreignKey = @ForeignKey(name = "FK_Contactos_Organizacion"))
    private Organizacion organizacion;

    public Contacto() {
    }

    public Contacto(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getDeseaRecibirPorWhatsapp() {
        return deseaRecibirPorWhatsapp;
    }

    public void setDeseaRecibirPorWhatsapp(Boolean deseaRecibirPorWhatsapp) {
        this.deseaRecibirPorWhatsapp = deseaRecibirPorWhatsapp;
    }

    public Boolean getDeseaRecibirPorMail() {
        return deseaRecibirPorMail;
    }

    public void setDeseaRecibirPorMail(Boolean deseaRecibirPorMail) {
        this.deseaRecibirPorMail = deseaRecibirPorMail;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    @Override
    public String toString() {
        String mail = email != null ? email : "-";
        String telef = telefono != null ? telefono : "-";
        return nombre + " " + apellido + " (Mail: " + mail + ", Telefono: " + telef + ")";
    }
}
