/*
Autores:
Juan Manuel Porcuna Martín 66%
Pablo Astudillo Fraga 16%
Alvaro Morales Perujo 16%
 */
package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    @Query("select u from UsuarioEntity u where u.usuario = :user and u.contrasena = :pwd")
    public UsuarioEntity autentica (@Param("user") String user, @Param("pwd") String pwd);

    @Query("SELECT u FROM UsuarioEntity u JOIN ClienteEntity c ON u.id = c.usuario.id")
    List<UsuarioEntity> findUsuariosClientes();

    @Query("SELECT u FROM UsuarioEntity u WHERE " +
            "(:ID IS NULL OR u.id = :ID) AND " +
            "(:usuario IS NULL OR u.usuario = :usuario) AND " +
            "(:Nombre IS NULL OR u.nombre = :Nombre) AND " +
            "(:Apellidos IS NULL OR u.apellidos = :Apellidos) AND " +
            "(:DNI IS NULL OR u.dni = :DNI) AND " +
            "(:Edad IS NULL OR u.edad = :Edad) AND " +
            "(:Sexo IS NULL OR u.sexo = :Sexo)" )
    List<UsuarioEntity> findByFiltro(@Param("ID") Integer ID,
                                     @Param("usuario") String usuario,
                                     @Param("Nombre") String Nombre,
                                     @Param("Apellidos") String Apellidos,
                                     @Param("DNI") String DNI,
                                     @Param("Edad") Integer Edad,
                                     @Param("Sexo") String Sexo);

    @Query("SELECT u FROM UsuarioEntity u WHERE u.contrasena IS NULL")
    List<UsuarioEntity> findBySinPassword();

    @Query("SELECT u FROM UsuarioEntity u WHERE u.trabajador IS NOT NULL AND (u.trabajador.rol.id = 2 OR u.trabajador.rol.id = 3)")
    List<UsuarioEntity> findByEntrenadores();

    @Query("SELECT u FROM UsuarioEntity u WHERE u.trabajador IS NOT NULL AND u.trabajador.rol.id = 1")
    List<UsuarioEntity> findByDietista();

    @Query("SELECT u FROM UsuarioEntity u WHERE u.usuario = :usuario")
    UsuarioEntity findByUsuario(@Param("usuario") String usuario);

}
