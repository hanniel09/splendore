package com.splendore.services.restaurant;

import com.splendore.domain.client.Client;
import com.splendore.domain.restaurant.clientRestaurant.ClientRestaurant;
import com.splendore.domain.restaurant.clientRestaurant.ClientRestaurantRequestDTO;
import com.splendore.domain.restaurant.clientRestaurant.ClientRestaurantResponseDTO;
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

    public ClientRestaurant findClientRestaurantById(UUID id) {
        return clientRestaurantRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Client not found with id: " + id));
    }

    public ClientRestaurant createClientRestaurant(ClientRestaurantRequestDTO clientRestaurant) {
        ClientRestaurant client= new ClientRestaurant();
        client.setName(clientRestaurant.name());
        client.setLastName(clientRestaurant.lastName());
        client.setTableNumber(clientRestaurant.tableNumber());
        client.setPrice(clientRestaurant.price());
        client.setQuantity(clientRestaurant.quantity());
        client.setEmail(clientRestaurant.email());
        client.setPhone(clientRestaurant.phone());
        client.setReservationDateTime(clientRestaurant.reservationDateTime());
        client.setStatus(clientRestaurant.status());
        client.setSpecialRequest(clientRestaurant.specialRequest());
        client.setTotalOrderPrice(clientRestaurant.totalOrderPrice());
        client.setCustomerLocationConsumed(clientRestaurant.customerLocationConsumed());
        client.setHasOrderOpen(clientRestaurant.hasOrderOpen());
        return clientRestaurantRepository.save(client);
    }

    public ClientRestaurant updateClientRestaurant(UUID id, ClientRestaurantRequestDTO clientRestaurant) {
        ClientRestaurant clientToUpdate= findClientRestaurantById(id);
        clientToUpdate.setName(clientRestaurant.name());
        clientToUpdate.setLastName(clientRestaurant.lastName());
        clientToUpdate.setTableNumber(clientRestaurant.tableNumber());
        clientToUpdate.setPrice(clientRestaurant.price());
        clientToUpdate.setQuantity(clientRestaurant.quantity());
        clientToUpdate.setEmail(clientRestaurant.email());
        clientToUpdate.setPhone(clientRestaurant.phone());
        clientToUpdate.setReservationDateTime(clientRestaurant.reservationDateTime());
        clientToUpdate.setStatus(clientRestaurant.status());
        clientToUpdate.setSpecialRequest(clientRestaurant.specialRequest());
        clientToUpdate.setTotalOrderPrice(clientRestaurant.totalOrderPrice());
        clientToUpdate.setCustomerLocationConsumed(clientRestaurant.customerLocationConsumed());
        clientToUpdate.setHasOrderOpen(clientRestaurant.hasOrderOpen());
        return clientRestaurantRepository.save(clientToUpdate);
    }

    public void deleteClientRestaurant(UUID id) {
        ClientRestaurant clientToDelete = findClientRestaurantById(id);
        clientRestaurantRepository.deleteById(clientToDelete.getId());
    }

}
