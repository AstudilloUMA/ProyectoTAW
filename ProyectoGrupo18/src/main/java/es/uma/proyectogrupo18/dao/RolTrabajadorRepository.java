/*
Autor:
Juan Manuel Porcuna Martín
 */
package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.RolTrabajadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolTrabajadorRepository extends JpaRepository<RolTrabajadorEntity, Integer> {
}
