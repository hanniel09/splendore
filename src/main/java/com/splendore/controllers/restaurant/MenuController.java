package com.splendore.controllers.restaurant;

import com.splendore.domain.restaurant.menu.Menu;
import com.splendore.domain.restaurant.menu.MenuRequestDTO;
import com.splendore.services.restaurant.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/Menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping()
    public ResponseEntity<List<Menu>> getAllMenuItems() {
        List<Menu> menus = menuService.getAllMenus();
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuItem(@PathVariable long id) {
        Menu menu = menuService.getMenuById(id);
        return ResponseEntity.ok(menu);
    }

    @PostMapping()
    public ResponseEntity<Menu> createMenuItem(@RequestBody MenuRequestDTO menuRequestDTO) {
     Menu menu = menuService.createNewMenu(menuRequestDTO);
     return ResponseEntity.ok(menu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> updateMenuItem(@PathVariable long id, @RequestBody MenuRequestDTO menuRequestDTO) {
        Menu menu = menuService.updateMenu(id, menuRequestDTO);
        return ResponseEntity.ok(menu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }
}
