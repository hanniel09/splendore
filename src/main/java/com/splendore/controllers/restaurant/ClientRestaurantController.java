package com.splendore.controllers.restaurant;

import com.splendore.domain.restaurant.clientRestaurant.ClientRestaurant;
import com.splendore.domain.restaurant.clientRestaurant.ClientRestaurantRequestDTO;
import com.splendore.services.restaurant.ClientRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ClientRestaurantController {

    @Autowired
    private ClientRestaurantService clientRestaurantService;

    @GetMapping()
    public ResponseEntity<List<ClientRestaurant>> findAllClientRestaurant() {
        List<ClientRestaurant> clientRestaurants = clientRestaurantService.findAll();
        return ResponseEntity.ok(clientRestaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientRestaurant> findClientRestaurantById(@PathVariable UUID id) {
        ClientRestaurant clientRestaurant = clientRestaurantService.findClientRestaurantById(id);
        return ResponseEntity.ok(clientRestaurant);
    }

    @PostMapping()
    public ResponseEntity<ClientRestaurant> createClientRestaurant(@RequestBody ClientRestaurantRequestDTO clientRestaurantRequestDTO) {
        ClientRestaurant clientRestaurant = clientRestaurantService.createClientRestaurant(clientRestaurantRequestDTO);
        return ResponseEntity.ok(clientRestaurant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientRestaurant> updateClientRestaurant(@PathVariable UUID id, @RequestBody ClientRestaurantRequestDTO clientRestaurantRequestDTO) {
        ClientRestaurant clientRestaurant = clientRestaurantService.updateClientRestaurant(id, clientRestaurantRequestDTO);
        return ResponseEntity.ok(clientRestaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientRestaurant(@PathVariable UUID id) {
        clientRestaurantService.deleteClientRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}
