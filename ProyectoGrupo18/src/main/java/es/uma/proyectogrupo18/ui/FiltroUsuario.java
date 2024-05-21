package es.uma.proyectogrupo18.ui;

public class FiltroUsuario {
    private Integer ID;
    private String usuario;
    private String Nombre;
    private String Apellidos;
    private String DNI;
    private Integer Edad;
    private String Sexo;
    private String Rol;

    public FiltroUsuario(){

    }

    public FiltroUsuario(Integer ID, String usuario, String Nombre, String Apellidos, String DNI, Integer Edad, String Sexo, String Rol) {
        this.ID = ID;
        this.usuario = usuario;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.DNI = DNI;
        this.Edad = Edad;
        this.Sexo = Sexo;
        this.Rol = Rol;
    }

    // Getters and Setters
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Integer getEdad() {
        return Edad;
    }

    public void setEdad(Integer Edad) {
        this.Edad = Edad;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }
}
