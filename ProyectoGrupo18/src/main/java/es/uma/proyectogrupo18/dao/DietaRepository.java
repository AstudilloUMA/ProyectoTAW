package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.DietaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DietaRepository extends JpaRepository<DietaEntity, Integer> {
    @Query("select d from DietaEntity d where d.trabajadorId = :filtro")
    public List<DietaEntity> buscarPorIdTrabajador(@Param("filtro")Integer filtro);

    @Query("select d from DietaEntity d where d.numComidas = :filtro1 and d.tipo like concat('%', :filtro2, '%')")
    public List<DietaEntity> filtrarDietas(@Param("filtro1")int filtro1, @Param("filtro2")String filtro2);

    @Query("select d from DietaEntity d where d.tipo like concat('%', :filtro2, '%')")
    public List<DietaEntity> filtrarDietasPorTipo(@Param("filtro2")String filtro2);
}
