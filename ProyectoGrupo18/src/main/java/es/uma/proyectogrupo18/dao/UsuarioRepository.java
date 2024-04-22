package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
}
