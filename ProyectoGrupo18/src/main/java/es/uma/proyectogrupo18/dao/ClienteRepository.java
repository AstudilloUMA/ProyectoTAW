package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.AdministradorEntity;
import es.uma.proyectogrupo18.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    @Query("select c from ClienteEntity c, UsuarioEntity u where c.usuarioId = u.id and lower(u.nombre) = lower(:user)" +
            " and u.contrasena = :pwd")
    public ClienteEntity autentica (@Param("user") String user, @Param("pwd") String pwd);

}
