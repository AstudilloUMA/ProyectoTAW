package es.uma.proyectogrupo18.ui;

public class Usuario {
    protected String user;
    protected String pwd;

    public Usuario () {}

    public Usuario (String user, String pwd) {
        this.user = user;
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
