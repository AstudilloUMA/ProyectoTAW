package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.RutinaSemanalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RutinaSemanalRepository extends JpaRepository<RutinaSemanalEntity, Integer> {
    @Query("select r from RutinaSemanalEntity r where r.trabajador.id = :trabajadorId")
    public List<RutinaSemanalEntity> findRutinasByTrabajadorId(Integer trabajadorId);
}
