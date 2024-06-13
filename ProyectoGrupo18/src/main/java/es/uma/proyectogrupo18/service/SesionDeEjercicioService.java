package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.SesionDeEjercicioRepository;
import es.uma.proyectogrupo18.dto.SesionDeEjercicioDTO;
import es.uma.proyectogrupo18.dto.TipoEjercicioDTO;
import es.uma.proyectogrupo18.entity.SesionDeEjercicioEntity;
import es.uma.proyectogrupo18.entity.TipoEjercicioEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class SesionDeEjercicioService {

    @Autowired
    private SesionDeEjercicioRepository sesionDeEjercicioRepository;

    @Autowired
    EjercicioService ejercicioService;

    @Autowired
    TrabajadorService trabajadorService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    RutinaService rutinaService;

    public Optional<SesionDeEjercicioDTO> getSesionDeEjercicioById(Integer id) {
        Optional<SesionDeEjercicioEntity> sesionDeEjercicioEntity = sesionDeEjercicioRepository.findById(id);
        return sesionDeEjercicioEntity.map(this::convertToDTO);
    }

    public SesionDeEjercicioDTO convertToDTO(SesionDeEjercicioEntity sesionDeEjercicioEntity){
        Integer id = sesionDeEjercicioEntity.getId();

        return SesionDeEjercicioDTO.builder()
                .id(id)
                .fecha(sesionDeEjercicioEntity.getFecha())
                .dia(sesionDeEjercicioEntity.getDia())
                .repeticiones(sesionDeEjercicioEntity.getRepeticiones())
                .cantidad(sesionDeEjercicioEntity.getCantidad())
                .orden((sesionDeEjercicioEntity.getOrden()))
                .peso(sesionDeEjercicioEntity.getPeso())
                .ejercicio(ejercicioService.getEjercicioById(sesionDeEjercicioEntity.getEjercicio().getId()).orElse(null))
                .trabajador(trabajadorService.getTrabajadorById(sesionDeEjercicioEntity.getTrabajador().getId()).orElse(null))
                .cliente(clienteService.getClienteById(sesionDeEjercicioEntity.getCliente().getId()).orElse(null))
                .rutina(rutinaService.getRutinaById(sesionDeEjercicioEntity.getRutina().getId()).orElse(null))
                .build();
    }


}
