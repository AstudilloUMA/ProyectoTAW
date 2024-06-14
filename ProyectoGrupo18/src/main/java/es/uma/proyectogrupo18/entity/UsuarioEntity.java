package es.uma.proyectogrupo18.entity;

import es.uma.proyectogrupo18.dto.DTO;
import es.uma.proyectogrupo18.dto.RolTrabajador;
import es.uma.proyectogrupo18.dto.Trabajador;
import es.uma.proyectogrupo18.dto.Usuario;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class UsuarioEntity implements Serializable, DTO<Usuario> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Usuario", nullable = false)
    private String usuario;

    @Column(name = "Contrasena")
    private String contrasena;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellidos")
    private String apellidos;

    @Column(name = "DNI")
    private String dni;

    @Column(name = "Edad")
    private Integer edad;

    @Column(name = "Sexo", length = 50)
    private String sexo;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private AdministradorEntity administrador;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private ClienteEntity cliente;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private TrabajadorEntity trabajador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public AdministradorEntity getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorEntity administrador) {
        this.administrador = administrador;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public TrabajadorEntity getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(TrabajadorEntity trabajador) {
        this.trabajador = trabajador;
    }

    public Usuario toDTO() {
        Usuario usuario = new Usuario();
        usuario.setId(this.id);
        usuario.setUsuario(this.usuario);
        usuario.setNombre(this.nombre);
        usuario.setApellidos(this.apellidos);
        usuario.setDni(this.dni);
        usuario.setEdad(this.edad);
        usuario.setSexo(this.sexo);
        usuario.setAdministrador(this.administrador != null ? this.administrador.toDTO() : null);
        usuario.setCliente(this.cliente != null ? this.cliente.toDTO() : null);
        usuario.setTrabajador(this.trabajador != null ? this.trabajador.toDTO() : null);

        return usuario;
    }

    public Usuario simpletoDTO() {
        Usuario usuario = new Usuario();
        usuario.setId(this.id);
        usuario.setUsuario(this.usuario);
        usuario.setNombre(this.nombre);
        usuario.setApellidos(this.apellidos);
        usuario.setDni(this.dni);
        usuario.setEdad(this.edad);
        usuario.setSexo(this.sexo);
        /*
        usuario.setAdministrador(this.administrador != null ? this.administrador.toDTO() : null);
        usuario.setCliente(this.cliente != null ? this.cliente.toDTO() : null);
        usuario.setTrabajador(this.trabajador != null ? this.trabajador.toDTO() : null);
        */
        return usuario;
    }
}