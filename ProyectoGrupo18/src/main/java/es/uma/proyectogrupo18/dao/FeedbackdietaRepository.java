package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.ClienteEntity;
import es.uma.proyectogrupo18.entity.FeedbackdietaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedbackdietaRepository extends JpaRepository<FeedbackdietaEntity, Integer> {

    @Query("SELECT f FROM FeedbackdietaEntity f WHERE f.cliente = :cliente")
    public FeedbackdietaEntity findByCliente(ClienteEntity cliente);
}
