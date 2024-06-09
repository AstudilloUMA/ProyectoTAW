package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.ClienteEntity;
import es.uma.proyectogrupo18.entity.FeedbackEntity;
import es.uma.proyectogrupo18.entity.SesionDeEjercicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Integer> {
    @Query("SELECT f FROM FeedbackEntity f WHERE f.sesion = :sesion AND f.cliente = :cliente ORDER BY f.sesion.dia, f.sesion.orden ASC")
    public FeedbackEntity findBySesion(SesionDeEjercicioEntity sesion, ClienteEntity cliente);

    @Query("SELECT f FROM FeedbackEntity f WHERE f.cliente = :cliente ORDER BY f.sesion.dia, f.sesion.orden ASC")
    public List<FeedbackEntity> findByCliente(ClienteEntity cliente);

}
