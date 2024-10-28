package com.splendore.services.restaurant;

import com.splendore.domain.restaurant.menu.Menu;
import com.splendore.domain.restaurant.menu.MenuRequestDTO;
import com.splendore.exceptions.NotFoundException;
import com.splendore.repositories.restaurant.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllMenus() {
     return menuRepository.findAll();
    }

    public Menu getMenuById(Long id) {
        return menuRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Menu not found with id: " + id)
        );
    }

    public Menu createNewMenu(MenuRequestDTO menuRequestDTO) {
        Menu menu = new Menu();
        menu.setName(menuRequestDTO.name());
        menu.setPrice(menuRequestDTO.price());
        menu.setIngredients(menuRequestDTO.ingredients());
        menu.setDescription(menuRequestDTO.description());
        return menuRepository.save(menu);
    }

    public Menu updateMenu(Long id, MenuRequestDTO menuRequestDTO) {
        Menu menu = getMenuById(id);
        menu.setName(menuRequestDTO.name());
        menu.setPrice(menuRequestDTO.price());
        menu.setIngredients(menuRequestDTO.ingredients());
        menu.setDescription(menuRequestDTO.description());
        return menuRepository.save(menu);
    }

    public void deleteMenu(Long id){
        menuRepository.deleteById(id);
    }
}
