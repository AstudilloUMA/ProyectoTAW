package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.ClienteRepository;
import es.uma.proyectogrupo18.dao.EjercicioRepository;
import es.uma.proyectogrupo18.dao.RutinaSemanalRepository;
import es.uma.proyectogrupo18.dao.SesionDeEjercicioRepository;
import es.uma.proyectogrupo18.dao.TrabajadorRepository;
import es.uma.proyectogrupo18.dto.RutinaSemanal;
import es.uma.proyectogrupo18.dto.SesionDeEjercicio;
import es.uma.proyectogrupo18.entity.SesionDeEjercicioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SesionDeEjercicioService extends DTOService<SesionDeEjercicio, SesionDeEjercicioEntity> {

    @Autowired
    private SesionDeEjercicioRepository sesionDeEjercicioRepository;

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RutinaSemanalRepository rutinaSemanalRepository;

    // Método para obtener todas las sesiones de ejercicio
    public List<SesionDeEjercicio> getAllSesionesDeEjercicio() {
        List<SesionDeEjercicioEntity> sesiones = sesionDeEjercicioRepository.findAll();
        return this.entidadesADTO(sesiones);
    }

    // Método para obtener una sesión de ejercicio por su ID
    public SesionDeEjercicio getSesionDeEjercicioById(Integer id) {
        SesionDeEjercicioEntity sesion = sesionDeEjercicioRepository.findById(id).orElse(null);
        if (sesion != null) {
            return sesion.toDTO();
        } else {
            return null;
        }
    }

    public List<SesionDeEjercicio> getSesionDeEjercicioByRutinaId(Integer id) {
        List<SesionDeEjercicioEntity> sesiones = sesionDeEjercicioRepository.findSesionesByRutina(id);
        return this.entidadesADTO(sesiones);
    }

    public List<SesionDeEjercicio> getSesionDeEjercicioByRutinaIdSinPersonalizar(Integer id, List<Integer> personalizadas) {
        List<SesionDeEjercicioEntity> sesiones = sesionDeEjercicioRepository.findSesionesByRutinaSinPersonalizar(id, personalizadas);
        return this.entidadesADTO(sesiones);
    }

    public List<SesionDeEjercicio> getSesionDeEjercicioByClienteId(Integer cliente) {
        List<SesionDeEjercicioEntity> sesiones = sesionDeEjercicioRepository.findSesionesByCliente(cliente);
        return this.entidadesADTO(sesiones);
    }

    public void desasignar(List<SesionDeEjercicio> sesion){
        for(SesionDeEjercicio sesionDeEjercicio: sesion){
            SesionDeEjercicioEntity sesionEntity = this.sesionDeEjercicioRepository.findById(sesionDeEjercicio.getId()).orElse(null);
            if(sesionEntity != null){
                sesionEntity.setCliente(null);
                sesionEntity.setPeso(null);
                this.sesionDeEjercicioRepository.save(sesionEntity);
            }
        }
    }


    // Método para borrar una sesión de ejercicio
    public void deleteSesionDeEjercicio(Integer id) {
        sesionDeEjercicioRepository.deleteById(id);
    }

    public void deleteAll(List<SesionDeEjercicio> sesiones){
        for(SesionDeEjercicio sesion: sesiones){
            sesionDeEjercicioRepository.deleteById(sesion.getId());
        }
    }

    // Método para guardar una sesión de ejercicio
    public Integer guardarSesionDeEjercicio(SesionDeEjercicio sesionDeEjercicio) {
        SesionDeEjercicioEntity sesionEntity = this.sesionDeEjercicioRepository.findById(sesionDeEjercicio.getId()).orElse(new SesionDeEjercicioEntity());
        sesionEntity.setFecha(sesionDeEjercicio.getFecha());
        sesionEntity.setDia(sesionDeEjercicio.getDia());
        sesionEntity.setRepeticiones(sesionDeEjercicio.getRepeticiones());
        sesionEntity.setCantidad(sesionDeEjercicio.getCantidad());
        sesionEntity.setOrden(sesionDeEjercicio.getOrden());
        sesionEntity.setPeso(sesionDeEjercicio.getPeso());
        sesionEntity.setEjercicio(this.ejercicioRepository.findById(sesionDeEjercicio.getEjercicio().getId()).orElse(null));
        sesionEntity.setTrabajador(this.trabajadorRepository.findById(sesionDeEjercicio.getTrabajador().getId()).orElse(null));
        sesionEntity.setCliente(sesionDeEjercicio.getCliente() != null ? this.clienteRepository.findById(sesionDeEjercicio.getCliente().getId()).orElse(null) : null);
        sesionEntity.setRutina(this.rutinaSemanalRepository.findById(sesionDeEjercicio.getRutina().getId()).orElse(null));
        this.sesionDeEjercicioRepository.save(sesionEntity);
        return sesionEntity.getId();
    }
}
