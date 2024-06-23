/*
 * 
 *  Alvaro Morales Perujo -> 70%
 *  Miguel Sanchez Hontoria -> 30%
 */

package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.ComidaRepository;
import es.uma.proyectogrupo18.dao.MenuRepository;
import es.uma.proyectogrupo18.dto.Menu;
import es.uma.proyectogrupo18.entity.MenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService extends DTOService<Menu, MenuEntity> {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ComidaRepository comidaRepository;

    // Método para obtener todos los menús
    public List<Menu> getAllMenus() {
        List<MenuEntity> menus = menuRepository.findAll();
        return this.entidadesADTO(menus);
    }

    // Método para obtener un menú por su ID
    public Menu getMenuById(Integer id) {
        MenuEntity menu = menuRepository.findById(id).orElse(null);
        if (menu != null) {
            return menu.toDTO();
        } else {
            return null;
        }
    }

    // Método para borrar un menú
    public void deleteMenu(Integer id) {
        menuRepository.deleteById(id);
    }

    // Método para guardar un menú
    public void guardarMenu(Menu menu) {
        MenuEntity menuEntity = this.menuRepository.findById(menu.getId()).orElse(new MenuEntity());
        menuEntity.setId(menu.getId());
        menuEntity.setComida(this.comidaRepository.findById(menu.getComida().getId()).orElse(null));
        menuEntity.setIngredientes(menu.getIngredientes());
        menuEntity.setPreparacion(menu.getPreparacion());
        this.menuRepository.save(menuEntity);
    }
}
