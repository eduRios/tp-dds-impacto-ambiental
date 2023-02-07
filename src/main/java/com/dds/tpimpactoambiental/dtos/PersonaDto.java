package com.dds.tpimpactoambiental.dtos;


import com.dds.tpimpactoambiental.enums.EnumUtils;
import com.dds.tpimpactoambiental.model.Persona;

public class PersonaDto extends BaseEntityDto {
    private String nombre;
    private String apellido;
    private IdTextPair tipoDocumento;
    private String documento;

    private PersonaDto(Persona persona) {
        super(persona);
    }

    public static PersonaDto from(Persona persona) {
        PersonaDto dto = new PersonaDto(persona);
        dto.setNombre(persona.getNombre());
        dto.setApellido(persona.getApellido());
        dto.setTipoDocumento(EnumUtils.enumToIdTextPair(persona.getTipoDocumento()));
        dto.setDocumento(persona.getDocumento());
        return dto;
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

    public IdTextPair getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(IdTextPair tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
