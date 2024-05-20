package es.uma.proyectogrupo18.entity;

import es.uma.proyectogrupo18.ui.Usuario;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "taw", catalog = "")
public class UsuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "Usuario")
    private String usuario;
    @Basic
    @Column(name = "Contrasena")
    private String contrasena;
    @Basic
    @Column(name = "Nombre")
    private String nombre;
    @Basic
    @Column(name = "Apellidos")
    private String apellidos;
    @Basic
    @Column(name = "DNI")
    private String dni;
    @Basic
    @Column(name = "Edad")
    private Integer edad;
    @Basic
    @Column(name = "Sexo")
    private String sexo;
    @OneToOne(mappedBy = "usuarioByUsuarioId")
    private AdministradorEntity administradorById;
    @OneToOne(mappedBy = "usuarioByUsuarioId")
    private ClienteEntity clienteById;
    @OneToOne(mappedBy = "usuarioByUsuarioId")
    private TrabajadorEntity trabajadorById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return id == that.id && Objects.equals(usuario, that.usuario) && Objects.equals(contrasena, that.contrasena) && Objects.equals(nombre, that.nombre) && Objects.equals(apellidos, that.apellidos) && Objects.equals(dni, that.dni) && Objects.equals(edad, that.edad) && Objects.equals(sexo, that.sexo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, contrasena, nombre, apellidos, dni, edad, sexo);
    }

    public AdministradorEntity getAdministradorById() {
        return administradorById;
    }

    public void setAdministradorById(AdministradorEntity administradorById) {
        this.administradorById = administradorById;
    }

    public ClienteEntity getClienteById() {
        return clienteById;
    }

    public void setClienteById(ClienteEntity clienteById) {
        this.clienteById = clienteById;
    }

    public TrabajadorEntity getTrabajadorById() {
        return trabajadorById;
    }

    public void setTrabajadorById(TrabajadorEntity trabajadorById) {
        this.trabajadorById = trabajadorById;
    }
}
