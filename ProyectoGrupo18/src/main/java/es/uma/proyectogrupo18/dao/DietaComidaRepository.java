/*
Autor:
Miguel Sánchez Hontoria
 */

package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.ComidaEntity;
import es.uma.proyectogrupo18.entity.DietaComidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DietaComidaRepository extends JpaRepository<DietaComidaEntity, Integer> {

    @Query("select d from DietaComidaEntity d where d.comida =:filtro")
    public DietaComidaEntity findDietaComida(@Param("filtro") ComidaEntity filtro);

}
