package es.uma.proyectogrupo18.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tipo_ejercicio")
public class TipoEjercicioEntity {
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

}