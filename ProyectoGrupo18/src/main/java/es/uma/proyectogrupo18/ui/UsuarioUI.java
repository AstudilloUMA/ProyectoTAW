/*
AUTOR --> Pablo Astudillo Fraga


 */

package es.uma.proyectogrupo18.ui;

public class UsuarioUI {
    protected String user;
    protected String pwd;

    public UsuarioUI() {}

    public UsuarioUI(String user, String pwd) {
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
