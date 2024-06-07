package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.EjercicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EjercicioRepository extends JpaRepository<EjercicioEntity, Integer> {

    @Query("select e.nombre from EjercicioEntity e order by e.nombre")
    public List<String> findAllOrdered();

    @Query("select e from EjercicioEntity e where e.nombre = :nombre")
    public EjercicioEntity findByName(String nombre);
}
