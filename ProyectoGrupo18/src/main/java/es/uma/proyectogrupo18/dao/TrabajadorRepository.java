package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.TrabajadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrabajadorRepository extends JpaRepository<TrabajadorEntity, Integer> {

    @Query("SELECT t FROM TrabajadorEntity t WHERE t.rol.id != 1")
    public Iterable<TrabajadorEntity> findEntrenadores();

}
