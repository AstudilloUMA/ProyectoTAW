package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.TipoEjercicioEntity;
import es.uma.proyectogrupo18.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoEjercicioRepository extends JpaRepository<TipoEjercicioEntity, Integer> {
    @Query("SELECT u FROM TipoEjercicioEntity u WHERE " +
            "(:Name IS NULL OR u.tipo = :Name)")
    TipoEjercicioEntity findByNombre(@Param("Name") String Name);

}
