package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.MenuRepository;
import es.uma.proyectogrupo18.dto.MenuDTO;
import es.uma.proyectogrupo18.entity.MenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ComidaService comidaService;

    public Optional<MenuDTO> getMenuById(Integer id) {
        Optional<MenuEntity> menuEntity = menuRepository.findById(id);
        return menuEntity.map(this::convertToDTO);
    }

    public MenuDTO convertToDTO(MenuEntity menuEntity) {
        return MenuDTO.builder()
                .id(menuEntity.getId())
                .comida(comidaService.convertToDTO(menuEntity.getComida()))
                .ingredientes(menuEntity.getIngredientes())
                .preparacion(menuEntity.getPreparacion())
                .build();
    }
}