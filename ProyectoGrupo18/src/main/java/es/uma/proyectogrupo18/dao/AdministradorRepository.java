package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.AdministradorEntity;
import es.uma.proyectogrupo18.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdministradorRepository extends JpaRepository<AdministradorEntity, Integer> {

    @Query("select a from AdministradorEntity a, UsuarioEntity u where a.usuarioId = u.id and lower(u.nombre) = lower(:user)" +
            " and u.contrasena = :pwd")
    public AdministradorEntity autentica (@Param("user") String user, @Param("pwd") String pwd);
}
