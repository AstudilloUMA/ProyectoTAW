package es.uma.proyectogrupo18.ui;
public class FiltroComida {
    private Integer id;
    private String nombre;
    private Integer calorias;
    private Boolean comida;

    public FiltroComida() {
    }

    public FiltroComida(Integer id, String nombre, Integer calorias) {
        this.id = id;
        this.nombre = nombre;
        this.calorias = calorias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public Boolean getComida() {
        return comida;
    }

    public void setComida(Boolean comida) {
        this.comida = comida;
    }
}
