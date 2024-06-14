package es.uma.proyectogrupo18.entity;

import es.uma.proyectogrupo18.dto.DTO;
import es.uma.proyectogrupo18.dto.RolTrabajador;
import es.uma.proyectogrupo18.dto.Trabajador;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Entity
@Table(name = "rol_trabajador")
public class RolTrabajadorEntity implements Serializable, DTO<RolTrabajador> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Rol", nullable = false)
    private String rol;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrabajadorEntity> trabajadores = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<TrabajadorEntity> getUsuarios() {
        return trabajadores;
    }

    public void setTrabajadores(List<TrabajadorEntity> trabajadores) {
        this.trabajadores = trabajadores;
    }

    public RolTrabajador toDTO() {
        RolTrabajador rolTrabajador = new RolTrabajador();

        rolTrabajador.setId(this.id);
        rolTrabajador.setRol(this.rol);

        return rolTrabajador;
    }
}