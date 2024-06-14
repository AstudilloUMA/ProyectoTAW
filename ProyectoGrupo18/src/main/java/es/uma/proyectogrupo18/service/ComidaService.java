package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.ComidaRepository;
import es.uma.proyectogrupo18.dao.DietaRepository;
import es.uma.proyectogrupo18.dao.MenuRepository;
import es.uma.proyectogrupo18.dto.Comida;
import es.uma.proyectogrupo18.entity.ComidaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComidaService extends DTOService<Comida, ComidaEntity> {

    @Autowired
    private ComidaRepository comidaRepository;

    @Autowired
    private DietaRepository dietaRepository;

    @Autowired
    private MenuRepository menuRepository;

    // Método para obtener todas las comidas
    public List<Comida> getAllComidas() {
        List<ComidaEntity> comidas = comidaRepository.findAll();
        return this.entidadesADTO(comidas);
    }

    // Método para obtener una comida por su ID
    public Comida getComidaById(Integer id) {
        ComidaEntity comida = comidaRepository.findById(id).orElse(null);
        if (comida != null) {
            return comida.toDTO();
        } else {
            return null;
        }
    }

    // Método para borrar una comida
    public void deleteComida(Integer id) {
        comidaRepository.deleteById(id);
    }

    // Método para guardar una comida
    public void guardarComida(Comida comida) {
        Integer id = comida.getId();
        ComidaEntity comidaEntity = this.comidaRepository.findById(id).orElse(new ComidaEntity());
        comidaEntity.setId(id);
        comidaEntity.setNombre(comida.getNombre());
        comidaEntity.setKilocaloriasTotales(comida.getKilocaloriasTotales());
        comidaEntity.setOrden(comida.getOrden());
        comidaEntity.setDietas(this.dietaRepository.findAllById(comida.getDietas()));
        comidaEntity.setMenus(this.menuRepository.findAllById(comida.getMenus()));
        this.comidaRepository.save(comidaEntity);
    }
}
