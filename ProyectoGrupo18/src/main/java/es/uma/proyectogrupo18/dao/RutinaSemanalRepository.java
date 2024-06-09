/*
AUTOR--> Pablo Astudillo Fraga

 */

package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.RutinaSemanalEntity;
import es.uma.proyectogrupo18.entity.TrabajadorEntity;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RutinaSemanalRepository extends JpaRepository<RutinaSemanalEntity, Integer> {
    @Query("select r from RutinaSemanalEntity r where r.trabajador.id = :trabajadorId")
    public List<RutinaSemanalEntity> findRutinasByTrabajadorId(Integer trabajadorId);

    @Query("select r from RutinaSemanalEntity r where r.trabajador = :trabajador" +
            " and (:nombre is null or r.nombre = :nombre)" +
            " and (:fechaInicio is null or r.fechaInicio >= :fechaInicio)" +
            " and (:fechaFin is null or r.fechaFin <= :fechaFin)")
    public List<RutinaSemanalEntity> findRutinasFiltradas(TrabajadorEntity trabajador, String nombre, LocalDate fechaInicio, LocalDate fechaFin);

}
