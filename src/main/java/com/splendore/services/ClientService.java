package com.splendore.services;

import com.splendore.converter.ClientConverter;
import com.splendore.domain.address.Address;
import com.splendore.domain.client.Client;
import com.splendore.domain.client.ClientRequestDTO;
import com.splendore.domain.client.ClientResponseDTO;
import com.splendore.domain.reservation.Reservation;
import com.splendore.domain.rooms.Rooms;
import com.splendore.exceptions.NotFoundException;
import com.splendore.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private RoomsService roomsService;

    @Autowired
    private ReservationHelperService reservationHelperService;

    public List<ClientResponseDTO> getAllClients() {
        List<Client> clients = this.clientRepository.findAll();

        return clients.stream()
                .map(ClientConverter::toClientResponseDTO
                ).collect(Collectors.toList());
    }

    public Client getClientById(UUID id) {
        return this.clientRepository.findById(id).orElseThrow(
                        () -> new NotFoundException("Client not found with id " + id)

        );
    }

    public ClientResponseDTO createClient(ClientRequestDTO data) {
        Client client = new Client();
        client.setName(data.name());
        client.setLastName(data.lastName());
        client.setEmail(data.email());
        client.setPhone(data.phone());
        client.setRg(data.rg());
        client.setCpf(data.cpf());

        if (data.address() != null){
            client.setAddress(this.addressService.getAddressById(data.address().id()));
        } else {
            client.setAddress(new Address());
        }

        if (data.roomIds() != null && !data.roomIds().isEmpty()){
            client.setRooms(this.roomsService.getRoomsByIds(data.roomIds()));
        } else {
            client.setRooms(Collections.emptyList());
        }

        if (data.reservationIds() != null && !data.reservationIds().isEmpty()){
            client.setReservation(this.reservationHelperService.getReservationsByIds(data.reservationIds()));
        } else {
            client.setReservation(Collections.emptyList());
        }

        Client savedClient = this.clientRepository.save(client);

        return ClientConverter.toClientResponseDTO(savedClient);
    }

    public ClientResponseDTO updateClient(UUID id, ClientRequestDTO data) {
        Client client = this.getClientById(id);
        client.setName(data.name());
        client.setLastName(data.lastName());
        client.setEmail(data.email());
        client.setPhone(data.phone());
        client.setRg(data.rg());
        client.setCpf(data.cpf());
        if (data.address() != null){
            client.setAddress(this.addressService.getAddressById(data.address().id()));
        }
        if (data.roomIds() != null && !data.roomIds().isEmpty()){
            client.setRooms(this.roomsService.getRoomsByIds(data.roomIds()));
        }
        if (data.reservationIds() != null && !data.reservationIds().isEmpty()){
            client.setReservation(this.reservationHelperService.getReservationsByIds(data.reservationIds()));
        }
        Client savedClient = this.clientRepository.save(client);
        return ClientConverter.toClientResponseDTO(savedClient);
    }

    public void deleteClient(UUID id) {
        this.clientRepository.deleteById(id);
    }
}
