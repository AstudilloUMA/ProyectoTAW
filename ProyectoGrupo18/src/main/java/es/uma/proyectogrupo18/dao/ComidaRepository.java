/*
Autores:
Juan Manuel Porcuna Martín 50%
Miguel Sanchez Hontoria 50%
 */
package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.ComidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComidaRepository extends JpaRepository<ComidaEntity, Integer> {

    @Query("SELECT c FROM ComidaEntity c, DietaComidaEntity d where d.dietaCodigo.id = :dietaCodigo and d.comida.id = c.id")
    public List<ComidaEntity> findComidasByDietaCodigo(@Param("dietaCodigo") int dietaCodigo);

    @Query("SELECT c FROM ComidaEntity c WHERE " +
            "(:comidaNombre IS NULL OR c.nombre LIKE %:comidaNombre%) AND " +
            "(:comidaCal IS NULL OR c.kilocaloriasTotales = :comidaCal)")
    List<ComidaEntity> findByFiltro(@Param("comidaNombre") String comidaNombre,
                                    @Param("comidaCal") Integer comidaCal);
}
