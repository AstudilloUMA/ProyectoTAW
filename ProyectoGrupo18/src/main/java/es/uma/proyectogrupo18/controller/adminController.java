package es.uma.proyectogrupo18.controller;

import es.uma.proyectogrupo18.dao.*;
import es.uma.proyectogrupo18.entity.*;
import es.uma.proyectogrupo18.ui.FiltroUsuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
    protected AdministradorRepository administradorRepository;

    @Autowired
    protected RolTrabajadorRepositor rolTrabajadorRepositor;

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
    //////// CRUD USUARIOS  //////////////////////////////////////////////////////////////////////////
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
                             @RequestParam("RolPre") String RolPre,
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

            if(RolPre.equals("Cliente")) {
                this.clienteRepository.delete(this.clienteRepository.findById(id).orElse(null));
                usuario.setClienteById(null);
            }
            if(RolPre.equals("Admin")) {
                this.administradorRepository.delete(this.administradorRepository.findById(id).orElse(null));
                usuario.setAdministradorById(null);
            }
            if(RolPre.equals( "Dietista") || RolPre.equals("Entrenador Cross-training") || RolPre.equals("Entrenador Bodybuilding")) {
                usuario.setTrabajadorById(null);
                this.trabajadorRepository.delete(this.trabajadorRepository.findById(id).orElse(null));

            }

            if(Rol.equals("Admin")){
                this.usuarioRepository.saveAndFlush(usuario);
                UsuarioEntity usuarioC = this.usuarioRepository.findById(usuario.getId()).orElse(null);

                AdministradorEntity administrador = new AdministradorEntity();
                administrador.setUsuarioId(usuarioC.getId());
                administrador.setUsuarioByUsuarioId(usuarioC);
                usuarioC.setAdministradorById(administrador);

                this.usuarioRepository.saveAndFlush(usuarioC);
                this.administradorRepository.saveAndFlush(administrador);
            }



            if(Rol.equals("Cliente")){
                this.usuarioRepository.saveAndFlush(usuario);
                UsuarioEntity usuarioC = this.usuarioRepository.findById(usuario.getId()).orElse(null);

                ClienteEntity cliente = new ClienteEntity();
                cliente.setUsuarioId(usuarioC.getId());
                cliente.setUsuarioByUsuarioId(usuarioC);
                usuarioC.setClienteById(cliente);

                this.usuarioRepository.saveAndFlush(usuarioC);
                this.clienteRepository.saveAndFlush(cliente);


            }
            if(Rol.equals( "Dietista") || Rol.equals("Entrenador Cross-training") || Rol.equals("Entrenador Bodybuilding")){
                Integer rolId = Rol.equals( "Dietista")?1:Rol.equals("Entrenador Cross-training")?2:3;
                this.usuarioRepository.saveAndFlush(usuario);
                UsuarioEntity usuarioC = this.usuarioRepository.findById(usuario.getId()).orElse(null);

                TrabajadorEntity trabajador = new TrabajadorEntity();
                trabajador.setUsuarioId(usuarioC.getId());
                trabajador.setUsuarioByUsuarioId(usuarioC);
                trabajador.setRolId(rolId);
                trabajador.setRolTrabajadorByRolId(this.rolTrabajadorRepositor.findById(rolId).orElse(null));
                usuarioC.setTrabajadorById(trabajador);

                this.usuarioRepository.saveAndFlush(usuarioC);
                this.trabajadorRepository.saveAndFlush(trabajador);

            }

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

    //////// AUTENTICAR USUARIOS  //////////////////////////////////////////////////////////////////////////

    @GetMapping("/autenticar")
    public String doAutenticar (Model model, HttpSession session){
        String strTo = "adminAutenticar";
        if (!"admin".equals(session.getAttribute("tipo"))) {
            return "sinPermiso";
        } else {

            List<UsuarioEntity> usuarios = (List<UsuarioEntity>) this.usuarioRepository.findBySinPassword();
            model.addAttribute("usuarios", usuarios);


        }

        return strTo;

    }

    @GetMapping("/autenticacion")
    public String doAutenticacion (@RequestParam("id") Integer id, Model model, HttpSession session){
        String strTo = "adminAutenticacion";
        if (!"admin".equals(session.getAttribute("tipo"))) {
            return "sinPermiso";
        } else {
            UsuarioEntity usuario = this.usuarioRepository.findById(id).orElse(null);
            model.addAttribute("usuario",usuario);
        }

        return strTo;

    }

    @PostMapping("/autenticado")
    public String doAutenticado (@RequestParam("id") Integer id,
                             @RequestParam("passw") String passw,
                             HttpSession session) {

        String strTo = "redirect:/admin/autenticar";
        if (!"admin".equals(session.getAttribute("tipo"))) {
            return "sinPermiso";
        } else {
            UsuarioEntity usuario = this.usuarioRepository.findById(id).orElse(new UsuarioEntity());
            usuario.setContrasena(passw);
            this.usuarioRepository.saveAndFlush(usuario);
        }
        return strTo;
    }
    //////// ASIGNAR USUARIOS  //////////////////////////////////////////////////////////////////////////

    @GetMapping("/asignarLista")
    public String doListaAsignar (Model model, HttpSession session){
        String strTo = "adminAsignarLista";
        if (!"admin".equals(session.getAttribute("tipo"))) {
            return "sinPermiso";
        } else {
            List<UsuarioEntity> usuarios = (List<UsuarioEntity>) this.usuarioRepository.findByTrabajadores();
            model.addAttribute("usuarios", usuarios);
        }

        return strTo;

    }
}
