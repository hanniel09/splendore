package com.splendore.controllers;

import com.splendore.domain.rooms.Rooms;
import com.splendore.domain.rooms.RoomsRequestDTO;
import com.splendore.domain.rooms.RoomsResponseDTO;
import com.splendore.services.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomsController {

    @Autowired
    private RoomsService roomsService;

    @GetMapping
    public ResponseEntity<List<RoomsResponseDTO>> getRooms(){
        List<RoomsResponseDTO> rooms = roomsService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Rooms> getRoomById(@PathVariable int id){
        Rooms rooms = roomsService.getRoomById(id);
        return ResponseEntity.ok(rooms);
    }

    @PostMapping()
    public ResponseEntity<Rooms> createRoom(@RequestBody RoomsRequestDTO data){
        Rooms rooms = roomsService.createRoom(data);
        return ResponseEntity.ok(rooms);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Rooms> updateRoom(@PathVariable int id, @RequestBody RoomsRequestDTO data){
        Rooms rooms = roomsService.updateRoom(data, id);
        return ResponseEntity.ok(rooms);
    }
}
