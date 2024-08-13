package com.splendore.controllers;

import com.splendore.domain.client.Client;
import com.splendore.domain.client.ClientRequestDTO;
import com.splendore.domain.client.ClientResponseDTO;
import com.splendore.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping()
    public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
            List<ClientResponseDTO> clients = this.clientService.getAllClients();
            return ResponseEntity.ok().body(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable UUID id) {
        Client client = this.clientService.getClientById(id);
        return ResponseEntity.ok().body(client);
    }

    @PostMapping()
    public ResponseEntity<ClientRequestDTO> createClient(@RequestBody ClientRequestDTO data) {
        this.clientService.createClient(data);
        return ResponseEntity.ok().body(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientRequestDTO> updateClient(@PathVariable UUID id, @RequestBody ClientRequestDTO data) {
        this.clientService.updateClient(id, data);
        return ResponseEntity.ok().body(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        this.clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
