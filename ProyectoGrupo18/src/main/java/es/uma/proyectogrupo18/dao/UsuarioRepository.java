package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.AdministradorEntity;
import es.uma.proyectogrupo18.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    @Query("SELECT u FROM UsuarioEntity u JOIN ClienteEntity c ON u.id = c.usuarioId WHERE u.nombre = :user AND u.contrasena = :pwd")
    public UsuarioEntity autenticaCliente(@Param("user") String user, @Param("pwd") String pwd);

}
