package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.RutinaSemanalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RutinaSemanalRepository extends JpaRepository<RutinaSemanalEntity, Integer> {
    @Query("SELECT r FROM RutinaSemanalEntity r WHERE r.cliente.usuarioId = :id")
    public List<RutinaSemanalEntity> findByClienteId(int id);
}
