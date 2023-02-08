package com.dds.tpimpactoambiental.dtos;

public class ContactoDto extends BaseEntityDto {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Boolean deseaRecibirPorWhatsapp;
    private Boolean deseaRecibirPorMail;

    public ContactoDto() {
    }
/*
    private ContactoDto(Contacto contacto) {
        super(contacto);
    }

    public static ContactoDto from(Contacto contacto) {
        ContactoDto dto = new ContactoDto(contacto);
        dto.setNombre(contacto.getNombre());
        dto.setApellido(contacto.getApellido());
        dto.setEmail(contacto.getEmail());
        dto.setTelefono(contacto.getTelefono());
        dto.setDeseaRecibirPorWhatsapp(contacto.getDeseaRecibirPorWhatsapp());
        dto.setDeseaRecibirPorMail(contacto.getDeseaRecibirPorMail());
        return dto;
    }*/

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
}
