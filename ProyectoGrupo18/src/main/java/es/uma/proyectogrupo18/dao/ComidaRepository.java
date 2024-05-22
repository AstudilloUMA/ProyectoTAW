package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.ComidaEntity;
import es.uma.proyectogrupo18.entity.DietaComidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComidaRepository extends JpaRepository<ComidaEntity, Integer> {

    @Query("SELECT c FROM ComidaEntity c, DietaComidaEntity d where d.dietaByDietaCodigo.codigo = :dietaCodigo and d.comidaByComidaId.id = c.id")
    public List<ComidaEntity> findComidasByDietaCodigo(@Param("dietaCodigo") int dietaCodigo);

}
