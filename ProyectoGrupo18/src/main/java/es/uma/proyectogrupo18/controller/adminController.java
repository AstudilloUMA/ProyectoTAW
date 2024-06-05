package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.*;
import es.uma.proyectogrupo18.entity.*;
import es.uma.proyectogrupo18.ui.FiltroUsuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
    protected AdministradorRepository administradorRepository;


    @Autowired
    protected TrabajadorRepository trabajadorRepository;

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

        if(sexo!= null){
            if(sexo.equals("Cualquiera")){
                sexo = null;
            }
        }
        if(dni!= null){
            if(dni.equals("vacio")){
                dni = null;
            }
        }

        if(usuario!= null){
            if(usuario.equals("vacio")){
                usuario = null;
            }
        }

        if(nombre!= null){
            if(nombre.equals("vacio")){
                nombre = null;
            }
        }

        if(apellidos!= null){
            if(apellidos.equals("vacio")){
                apellidos = null;
            }
        }


        List<UsuarioEntity> usuariosRaw = usuarioRepository.findByFiltro(ID, usuario, nombre, apellidos, dni, Edad, sexo);

        List<FiltroUsuario> usuarios = new ArrayList<>();
        for (UsuarioEntity user : usuariosRaw) {
            String Rol  ="";
            if(user.getAdministradorById()!=null){
                Rol = "Admin";
            }
            if(user.getClienteById()!=null){
                Rol = "Cliente";
            }
            if(user.getTrabajadorById()!=null){
                Rol = "Trabajador";
            }
            usuarios.add(new FiltroUsuario(user.getId(), user.getUsuario(), user.getNombre(), user.getApellidos(), user.getDni(), user.getEdad(), user.getSexo(), Rol));
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

        String usuario = null,nombre=null,apellido=null,dni=null,sexo;
        if(filtro.getUsuario().isEmpty()) {
            usuario = "vacio";
        }else{
            usuario = filtro.getUsuario();
        }
        if(filtro.getNombre().isEmpty()) {
            nombre = "vacio";
        }else{
            nombre = filtro.getNombre();
        }
        if(filtro.getApellidos().isEmpty()) {
            apellido = "vacio";
        }else{
            apellido = filtro.getApellidos();
        }
        if(filtro.getDNI().isEmpty()){
            dni = "vacio";
        } else{
            dni = filtro.getDNI();
        }
        if(!filtro.getSexo().equals("Cualquiera")){
            sexo = filtro.getSexo();
        }else {
            sexo="Cualquiera";
        }
        return "redirect:/admin/Usuarios?ID=" + filtro.getID() + "&usuario=" + usuario + "&Nombre=" + nombre + "&Apellidos=" + apellido + "&DNI=" + dni + "&Edad=" + filtro.getEdad() + "&Sexo=" + sexo;
    }
    @GetMapping("/Crear")
    public String doNuevo(Model model, HttpSession session) {
        String strTo = "adminUsuario";
        if (!"admin".equals(session.getAttribute("tipo"))) {
            return "sinPermiso";
        } else {
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setId(-1);
            List<String> roles = new ArrayList<>();
            roles.add("Cliente");
            roles.add("Admin");
            roles.add("Dietista");
            roles.add("Entrenador Bodybuilding");
            roles.add("Entrenador Cross-training");
            model.addAttribute("roles", roles);
            model.addAttribute("usuario", usuario);
        }

        return strTo;
    }
    @GetMapping("/modificar")
    public String doEditar (@RequestParam("id") Integer id, Model model, HttpSession session) {
        String strTo = "adminUsuario";
        if (!"admin".equals(session.getAttribute("tipo"))) {
            return "sinPermiso";
        } else {

            UsuarioEntity usuario = this.usuarioRepository.findById(id).orElse(null);
            model.addAttribute("usuario", usuario);

            List<String> roles = new ArrayList<>();
            roles.add("Cliente");
            roles.add("Admin");
            roles.add("Dietista");
            roles.add("Entrenador Bodybuilding");
            roles.add("Entrenador Cross-training");
            model.addAttribute("roles", roles);

        }

        return strTo;
    }

    @PostMapping("/guardar")
    public String doGuardar (@RequestParam("id") Integer id,
                             @RequestParam("usuarioName") String usuarioName,
                             @RequestParam("Nombre") String Nombre,
                             @RequestParam("Apellidos") String Apellidos,
                             @RequestParam("DNI") String DNI,
                             @RequestParam("sexo") String sexo,
                             @RequestParam("edad") Integer edad,
                             @RequestParam("Rol") String Rol,
                             HttpSession session) {

        String strTo = "redirect:/admin/Usuarios";
        if (!"admin".equals(session.getAttribute("tipo"))) {
            return "sinPermiso";
        } else {
            UsuarioEntity usuario = this.usuarioRepository.findById(id).orElse(new UsuarioEntity());
            usuario.setUsuario(usuarioName);
            usuario.setNombre(Nombre);
            usuario.setApellidos(Apellidos);
            usuario.setDni(DNI);
            usuario.setSexo(sexo);
            usuario.setEdad(edad);

            if(Rol == "Admin"){
                AdministradorEntity admin = new AdministradorEntity();
                usuario.setAdministradorById(admin);
                admin.setUsuarioByUsuarioId(usuario);
                this.administradorRepository.save(admin);
            }
            if(Rol == "Cliente"){
                ClienteEntity cliente = new ClienteEntity();
                usuario.setClienteById(cliente);
                cliente.setUsuarioByUsuarioId(usuario);
                cliente.setEdad(usuario.getEdad());
            }
            if(Rol == "Dietista" && Rol == "Entrenador Cross-training" && Rol == "ntrenador Bodybuilding"){
                TrabajadorEntity trabajador = new TrabajadorEntity();
                usuario.setTrabajadorById(trabajador);
                trabajador.setUsuarioByUsuarioId(usuario);
                trabajador.setRol(Rol);
                this.trabajadorRepository.save(trabajador);
            }

            this.usuarioRepository.save(usuario);
        }

        return strTo;
    }

    @GetMapping("/eliminar")
    public String doBorrar (@RequestParam("id") Integer id, HttpSession session) {
        String strTo = "redirect:/admin/Usuarios";
        if (!"admin".equals(session.getAttribute("tipo"))) {
            return "sinPermiso";
        } else {
            this.usuarioRepository.deleteById(id);
        }
        return strTo;
    }
}
