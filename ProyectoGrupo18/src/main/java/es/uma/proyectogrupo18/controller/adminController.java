package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.AdministradorRepository;
import es.uma.proyectogrupo18.dao.ClienteRepository;
import es.uma.proyectogrupo18.dao.UsuarioRepository;
import es.uma.proyectogrupo18.entity.UsuarioEntity;
import es.uma.proyectogrupo18.ui.FiltroUsuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
    protected AdministradorRepository administradorRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    @GetMapping("/")
    public String adminHome(HttpSession httpSession) {
        if (!"admin".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        } else {
            return "adminHome";
        }
    }

    @GetMapping("/Usuarios")
    public String adminUser(@RequestParam(name = "ID", required = false) String id,
                            @RequestParam(name = "usuario", required = false) String usuario,
                            @RequestParam(name = "Nombre", required = false) String nombre,
                            @RequestParam(name = "Apellidos", required = false) String apellidos,
                            @RequestParam(name = "DNI", required = false) String dni,
                            @RequestParam(name = "Edad", required = false) String edad,
                            @RequestParam(name = "Sexo", required = false) String sexo,
                            Model model, HttpSession httpSession) {
        if (!"admin".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }
        Integer ID = null;
        if(id != null && !id.equals("null") && !edad.isEmpty()){
            ID = Integer.parseInt(id);
        }

        Integer Edad = null;
        if(edad != null && !edad.equals("null") && !edad.isEmpty()){
            Edad = Integer.valueOf(edad);
        }

        List<UsuarioEntity> usuariosRaw = usuarioRepository.findByFiltro(ID, usuario, nombre, apellidos, dni, null, sexo);

        List<FiltroUsuario> usuarios = new ArrayList<>();
        for (UsuarioEntity user : usuariosRaw) {
            usuarios.add(new FiltroUsuario(user.getId(), user.getUsuario(), user.getNombre(), user.getApellidos(), user.getDni(), user.getEdad(), user.getSexo(), "NOT YET"));
        }
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("filtro", new FiltroUsuario());

        return "adminUsuarios";
    }

    @RequestMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro") FiltroUsuario filtro, HttpSession httpSession){
        if (!"admin".equals(httpSession.getAttribute("tipo"))) {
            return "sinPermiso";
        }
        String usuario = null,nombre=null,apellido=null,dni=null,sexo=null;
        if(!filtro.getUsuario().equals("")){
            usuario = filtro.getUsuario();
        }
        if(!filtro.getNombre().equals("")){
            nombre = filtro.getNombre();
        }
        if(!filtro.getApellidos().equals("")){
            apellido = filtro.getApellidos();
        }
        if(!filtro.getDNI().equals("")){
            dni = filtro.getDNI();
        }
        if(!filtro.getSexo().equals("")){
            sexo = filtro.getSexo();
        }
        return "redirect:/admin/Usuarios?ID=" + filtro.getID() + "&usuario=" + usuario + "&Nombre=" + nombre + "&Apellidos=" + apellido + "&DNI=" + dni + "&Edad=" + filtro.getEdad() + "&Sexo=" + sexo;    }
}
