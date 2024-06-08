package es.uma.proyectogrupo18.ui;

public class FiltroCRUD {
    private Integer id;
    private Integer tipo;
    private Boolean ifComida;
    private Boolean ifEj;
    private Boolean ifSe;
    private String comidaNombre;
    private Integer comidaCalorias;
    private String ejTipo;
    private String ejNombre;
    private String seRepeticiones;
    private String seCantidad;
    private String seEjercicio;

    public FiltroCRUD(){

    }
    public FiltroCRUD(Integer id, Integer tipo,Boolean ifComida, Boolean ifEj, Boolean ifSe,String comidaNombre,Integer comidaCalorias, String ejTipo, String ejNombre,
                      String seRepeticiones, String seCantidad, String seEjercicio) {

        this.id = id;
        this.tipo = tipo;
        this.ifComida = ifComida;
        this.ifEj = ifEj;
        this.ifSe = ifSe;
        this.comidaNombre = comidaNombre;
        this.comidaCalorias = comidaCalorias;
        this.ejTipo = ejTipo;
        this.ejNombre = ejNombre;
        this.seRepeticiones = seRepeticiones;
        this.seCantidad = seCantidad;
        this.seEjercicio = seEjercicio;

    }
    //Comida
    public FiltroCRUD(Integer id, Integer tipo, String nombre, Integer calorias) {
        this.id = id;
        this.tipo = tipo;
        this.comidaNombre = nombre;
        this.comidaCalorias = calorias;
    }

    //ejercicio
    public FiltroCRUD(Integer id, Integer tipo, String tipoE, String nombre) {
        this.id = id;
        this.tipo = tipo;
        this.ejTipo = tipoE;
        this.ejNombre = nombre;
    }

    //sesion
    public FiltroCRUD(Integer id, Integer tipo, String repeticiones, String cantidad, String ejercicio) {
        this.id = id;
        this.tipo = tipo;
        this.seRepeticiones = repeticiones;
        this.seCantidad = cantidad;
        this.seEjercicio = ejercicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    // Getters y Setters para ifComida
    public Boolean getIfComida() {
        return ifComida;
    }

    public void setIfComida(Boolean ifComida) {
        this.ifComida = ifComida;
    }

    // Getters y Setters para ifEj
    public Boolean getIfEj() {
        return ifEj;
    }

    public void setIfEj(Boolean ifEj) {
        this.ifEj = ifEj;
    }

    // Getters y Setters para ifSe
    public Boolean getIfSe() {
        return ifSe;
    }

    public void setIfSe(Boolean ifSe) {
        this.ifSe = ifSe;
    }

    public String getComidaNombre() {
        return comidaNombre;
    }

    public void setComidaNombre(String comidaNombre) {
        this.comidaNombre = comidaNombre;
    }

    public Integer getComidaCalorias() {
        return comidaCalorias;
    }

    public void setComidaCalorias(Integer comidaCalorias) {
        this.comidaCalorias = comidaCalorias;
    }

    public String getejTipo() {
        return ejTipo;
    }

    public void setejTipo(String tipo) {
        this.ejTipo = tipo;
    }

    public String getejNombre() {
        return ejNombre;
    }

    public void setejNombre(String nombre) {
        this.ejNombre = nombre;
    }

    public String getseRepeticiones() {
        return seRepeticiones;
    }
    public void setseRepeticiones(String repeticiones) {
        this.seRepeticiones = repeticiones;
    }

    public String getseCantidad() {
        return seCantidad;
    }

    public void setseCantidad(String cantidad) {
        this.seCantidad = cantidad;
    }

    public String getseEjercicio() {
        return seEjercicio;
    }

    public void setseEjercicio(String ejercicio) {
        this.seEjercicio = ejercicio;
    }

}

