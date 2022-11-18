package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="organizacion")
public class Organizacion {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String razonSocial;
    private String tipo;
    @ElementCollection
    private List<String> sectores;
    private String clasificacion;

    @OneToMany(mappedBy="organizacion", fetch= FetchType.EAGER)
    private List<Matricula> matriculas;

    @OneToMany
    @JoinColumn(name = "organizacion_id")
    private List<DatosActividad> datosActividadList;
    public Organizacion() {
    }

    public Organizacion(String razonSocial, String tipo, List<String> sectores, String clasificacion) {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.sectores = sectores;
        this.clasificacion = clasificacion;
    }

    public Organizacion(String razonSocial, String tipo, List<String> sectores, String clasificacion, List<Matricula> matriculas) {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.sectores = sectores;
        this.clasificacion = clasificacion;
        this.matriculas = matriculas;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getTipo() {
        return tipo;
    }

    public List<String> getSectores() {
        return sectores;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void addMatricula(Matricula matricula) {
        matricula.setOrganizacion(this);
        matriculas.add(matricula);
    }

    public List<DatosActividad> getDatosActividadList() {
        return datosActividadList;
    }

    public void setDatosActividadList(List<DatosActividad> datosActividadList) {
        this.datosActividadList = datosActividadList;
    }
}
