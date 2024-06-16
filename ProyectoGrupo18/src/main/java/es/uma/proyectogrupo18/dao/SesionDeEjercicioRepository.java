/*
Autores:
Juan Manuel Porcuna Martín 20%
Pablo Astudillo Fraga 80%
 */
package es.uma.proyectogrupo18.dao;

import es.uma.proyectogrupo18.entity.ClienteEntity;
import es.uma.proyectogrupo18.entity.RutinaSemanalEntity;
import es.uma.proyectogrupo18.entity.SesionDeEjercicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SesionDeEjercicioRepository extends JpaRepository<SesionDeEjercicioEntity, Integer> {
    @Query("select s from SesionDeEjercicioEntity s where s.ejercicio = :ejercicio")
    public List<SesionDeEjercicioEntity> findSesionesByEjercicioId(@Param("ejercicio")int ejercicio);

    @Query("SELECT s FROM SesionDeEjercicioEntity s WHERE s.rutina.id = :id ORDER BY s.dia, s.orden ASC")
    List<SesionDeEjercicioEntity> findSesionesByRutina(@Param("id") Integer id);

    @Query("SELECT s FROM SesionDeEjercicioEntity s WHERE s.rutina.id = :id AND (s.cliente IS NULL) AND (s.id NOT IN :personalizadas) ORDER BY s.dia, s.orden ASC")
    List<SesionDeEjercicioEntity> findSesionesByRutinaSinPersonalizar(@Param("id") Integer id, @Param("personalizadas") List<Integer> personalizadas);

    @Query("SELECT s FROM SesionDeEjercicioEntity s WHERE s.cliente.id = :id ORDER BY s.dia, s.orden ASC")
    List<SesionDeEjercicioEntity> findSesionesByCliente(@Param("id") Integer id);
    
    @Query("SELECT s FROM SesionDeEjercicioEntity s " +
    "JOIN s.ejercicio e " +
    "WHERE " +
    "(:seRep IS NULL OR s.repeticiones LIKE %:seRep%) AND " +
    "(:seCan IS NULL OR s.cantidad LIKE %:seCan%) AND " +
    "(:seEj IS NULL OR e.nombre LIKE %:seEj%) ")
List<SesionDeEjercicioEntity> findByFiltro(@Param("seRep") String seRep,
                                       @Param("seCan") String seCan,
                                       @Param("seEj") String seEj);
}
