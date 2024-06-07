package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.RutinaSemanalEntrenamientoEntity;
import es.uma.proyectogrupo18.entity.SesionDeEntrenamientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SesionDeEntrenamientoRepository extends JpaRepository<SesionDeEntrenamientoEntity, Integer> {
    @Query("select s from SesionDeEntrenamientoEntity s where s in :rutinas order by s.dia")
    public List<SesionDeEntrenamientoEntity> orderSesiones(@Param("rutinas") List<SesionDeEntrenamientoEntity> rutinas);
}
