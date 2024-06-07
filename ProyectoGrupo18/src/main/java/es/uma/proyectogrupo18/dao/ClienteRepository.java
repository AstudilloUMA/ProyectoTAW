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

    @Query("select c from ClienteEntity c where c.entrenador = :entrenador")
    public List<ClienteEntity> findClientesByEntrenador(@Param("entrenador") TrabajadorEntity entrenador);

    @Query("select c from ClienteEntity c where c.dietista = :dietista")
    public List<ClienteEntity> findClientesByDietista(@Param("dietista") TrabajadorEntity dietista);

}
