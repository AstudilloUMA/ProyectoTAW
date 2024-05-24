package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {

    @Query("select m from MenuEntity m where m.id = :filtro")
    public MenuEntity seleccionarMenu(@Param("filtro") int filtro);
}
