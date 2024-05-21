package es.uma.proyectogrupo18.ui;

public class FiltroDieta {

    protected String filtro1;
    protected String filtro2;

    protected Integer id;

    public FiltroDieta() {
        this.filtro1 = "";
        this.filtro2 = "";
        this.id = 0;
    }

    public String getFiltro1() {
        return filtro1;
    }

    public void setFiltro1(String filtro1) {
        this.filtro1 = filtro1;
    }

    public String getFiltro2() {
        return filtro2;
    }

    public void setFiltro2(String filtro2) {
        this.filtro2 = filtro2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIntegerComidas () {
        if (filtro1.isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(filtro1);
        }
    }
    public boolean estaVacio () {
        return this.filtro1.isEmpty() && this.filtro2.isEmpty();
    }
}
