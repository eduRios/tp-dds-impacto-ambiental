package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="miembro")
public class Miembro {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String nroDocumento;

    public Long getId() {
        return id;
    }

    @OneToMany(mappedBy="miembro", fetch= FetchType.EAGER)
    private List<Matricula> matriculas;

    public Miembro() {
    }

    public Miembro(String nombre, String apellido, String tipoDocumento, String nroDocumento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }
    public void addMatricula(Matricula matricula) {
        matricula.setMiembro(this);
        matriculas.add(matricula);
    }

}
