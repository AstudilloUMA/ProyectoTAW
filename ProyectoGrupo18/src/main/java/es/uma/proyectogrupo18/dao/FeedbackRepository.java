package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Integer> {
}
