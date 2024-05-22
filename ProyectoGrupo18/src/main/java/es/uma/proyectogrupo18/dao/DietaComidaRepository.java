package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.DietaComidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DietaComidaRepository extends JpaRepository<DietaComidaEntity, Integer> {

}
