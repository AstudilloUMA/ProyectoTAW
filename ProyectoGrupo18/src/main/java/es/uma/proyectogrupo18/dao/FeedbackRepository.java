/*
AUTOR --> Pablo Astudillo Fraga

 */

package es.uma.proyectogrupo18.dao;


import es.uma.proyectogrupo18.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Integer> {
    @Query("SELECT f FROM FeedbackEntity f WHERE f.sesion = :idSesion AND f.cliente.id = :idCliente ORDER BY f.sesion.dia, f.sesion.orden ASC")
    public FeedbackEntity findBySesion(Integer idSesion, Integer idCliente);

    @Query("SELECT f FROM FeedbackEntity f WHERE f.cliente.id = :id ORDER BY f.sesion.dia, f.sesion.orden ASC")
    public List<FeedbackEntity> findByCliente(Integer id);

}
