package es.uma.proyectogrupo18.entity;

import es.uma.proyectogrupo18.dto.DTO;
import es.uma.proyectogrupo18.dto.RolTrabajador;
import es.uma.proyectogrupo18.dto.TipoEjercicio;
import es.uma.proyectogrupo18.dto.Trabajador;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tipo_ejercicio")
public class TipoEjercicioEntity implements Serializable, DTO<TipoEjercicio> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Tipo", nullable = false)
    private String tipo;

    @OneToMany(mappedBy = "tipo")
    private Set<EjercicioEntity> ejercicios = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<EjercicioEntity> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(Set<EjercicioEntity> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public TipoEjercicio toDTO() {
        TipoEjercicio tipoEjercicio = new TipoEjercicio();
        tipoEjercicio.setId(this.id);
        tipoEjercicio.setTipo(this.tipo);

        List<Integer> ejercicios = new ArrayList<>();
        this.ejercicios.forEach(ej -> ejercicios.add(ej.getId()));
        tipoEjercicio.setEjercicios(ejercicios);

        return tipoEjercicio;
    }
}