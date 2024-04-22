package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {
}
