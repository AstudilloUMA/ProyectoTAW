package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.TrabajadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabajadorRepository extends JpaRepository<TrabajadorEntity, Integer> {

}
