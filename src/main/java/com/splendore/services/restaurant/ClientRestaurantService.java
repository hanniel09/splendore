package com.splendore.services.restaurant;

import com.splendore.domain.restaurant.clientRestaurant.ClientRestaurant;
import com.splendore.exceptions.NotFoundException;
import com.splendore.repositories.restaurant.ClientRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientRestaurantService {

    @Autowired
    private ClientRestaurantRepository clientRestaurantRepository;

    public List<ClientRestaurant> findAll() {
        return clientRestaurantRepository.findAll();
    }

    public ClientRestaurant findById(UUID id) {
        return clientRestaurantRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Client not found with id: " + id));
    }

    public ClientRestaurant createClientRestaurant(ClientRestaurant clientRestaurant) {
        ClientRestaurant client= new ClientRestaurant();
        return clientRestaurantRepository.save(clientRestaurant);
    }
}
