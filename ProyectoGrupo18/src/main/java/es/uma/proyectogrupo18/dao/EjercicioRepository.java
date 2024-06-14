/*
Autores:
Juan Manuel Porcuna Martín 25%
Pablo Astudillo Fraga 75%
 */
package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.EjercicioEntity;
import es.uma.proyectogrupo18.entity.TipoEjercicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EjercicioRepository extends JpaRepository<EjercicioEntity, Integer> {


    @Query("select e from EjercicioEntity e order by e.nombre")
    public List<EjercicioEntity> findAllOrdered();

    @Query("select e from EjercicioEntity e where e.nombre = :nombre")
    public EjercicioEntity findByName(String nombre);

    @Query("select e from EjercicioEntity e where e.tipo.id = :id")
    public List<EjercicioEntity> findByTipo(Integer id);

    @Query("SELECT e FROM EjercicioEntity e JOIN e.tipo t WHERE " +
            "(:ejTipo IS NULL OR t.tipo = :ejTipo) AND " +
            "(:ejNombre IS NULL OR e.nombre LIKE %:ejNombre%)")
    List<EjercicioEntity> findByFiltro(@Param("ejTipo") String ejTipo,
                                       @Param("ejNombre") String ejNombre);

}
