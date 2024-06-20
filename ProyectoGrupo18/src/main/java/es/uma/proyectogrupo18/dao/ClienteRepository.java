/*
AUTORES -->
    -> Miguel Sanchez Hontoria 30%
    -> Pablo Astudillo Fraga 70%
 */

package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.ClienteEntity;
import es.uma.proyectogrupo18.entity.TrabajadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    @Query("select c from ClienteEntity c where c not in :clientes")
    public List<ClienteEntity> findUsuariosSinRutina(@Param("clientes") List<ClienteEntity> clientes);

    @Query("select c from ClienteEntity c where c.entrenador.id = :id")
    public List<ClienteEntity> findClientesByEntrenador(@Param("id") Integer id);

    @Query("select c from ClienteEntity c where c.dietista.id = :id")
    public List<ClienteEntity> findClientesByDietista(@Param("id") Integer id);

}
