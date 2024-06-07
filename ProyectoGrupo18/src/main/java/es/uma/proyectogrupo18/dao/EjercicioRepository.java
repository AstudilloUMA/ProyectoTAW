package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.EjercicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EjercicioRepository extends JpaRepository<EjercicioEntity, Integer> {

    @Query("SELECT e FROM EjercicioEntity e WHERE " +
            "(:ejTipo IS NULL OR e.tipo = :ejTipo) AND " +
            "(:ejNombre IS NULL OR e.nombre LIKE %:ejNombre%)")
    List<EjercicioEntity> findByFiltro(@Param("ejTipo") Integer ejTipo,
                                       @Param("ejNombre") String ejNombre);
}
