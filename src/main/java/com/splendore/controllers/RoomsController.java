package com.splendore.controllers;

import com.splendore.domain.rooms.Rooms;
import com.splendore.domain.rooms.RoomsRequestDTO;
import com.splendore.domain.rooms.RoomsResponseDTO;
import com.splendore.services.RoomsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomsController {

    @Autowired
    private RoomsService roomsService;

    @Operation(
            summary = "Get all Rooms",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Returning all rooms of the splendore",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(name = "RoomExample",
                                            value =
                                                    "{ \"id\": 1, \"roomsStatus\": \"AVAILABLE\", \"floorNumber\": 3, " +
                                                            "\"roomsNumber\": 303, \"roomsLevel\": \"GOLD\", \"roomsServices\":" +
                                                            " \"BREAKFAST\", \"bedsInRoomNumber\": 2 }"
                                    )
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<RoomsResponseDTO>> getRooms() {
        List<RoomsResponseDTO> rooms = roomsService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @Operation(
            summary = "Get Room by ID",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Returning Room by ID of the splendore",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(name = "RoomExample",
                                            value =
                                                    "{ \"id\": 1, \"roomsStatus\": \"AVAILABLE\", \"floorNumber\": 3, " +
                                                            "\"roomsNumber\": 303, \"roomsLevel\": \"GOLD\", \"roomsServices\":" +
                                                            " \"BREAKFAST\", \"bedsInRoomNumber\": 2 }"
                                    )
                            )
                    )
            }
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<Rooms> getRoomById(@PathVariable int id) {
        Rooms rooms = roomsService.getRoomById(id);
        return ResponseEntity.ok(rooms);
    }

    @Operation(
            summary = "Create a Room",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Return the Room created",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(name = "RoomExample",
                                            value =
                                                    "{ \"id\": 1, \"roomsStatus\": \"AVAILABLE\", \"floorNumber\": 3, " +
                                                            "\"roomsNumber\": 303, \"roomsLevel\": \"GOLD\", \"roomsServices\":" +
                                                            " \"BREAKFAST\", \"bedsInRoomNumber\": 2 }"
                                    )
                            )
                    )
            }
    )
    @PostMapping()
    public ResponseEntity<Rooms> createRoom(@RequestBody RoomsRequestDTO data) {
        Rooms rooms = roomsService.createRoom(data);
        return ResponseEntity.ok(rooms);
    }

    @Operation(
            summary = "Update a Room",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Return the Room updated",
                            content = @Content(
                                    mediaType = "application/json, Long ID",
                                    examples = @ExampleObject(name = "RoomExample",
                                            value =
                                                    "{ \"id\": 1, \"roomsStatus\": \"AVAILABLE\", \"floorNumber\": 3, " +
                                                            "\"roomsNumber\": 303, \"roomsLevel\": \"GOLD\", \"roomsServices\":" +
                                                            " \"BREAKFAST\", \"bedsInRoomNumber\": 2 }"
                                    )
                            )
                    )
            }
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity<Rooms> updateRoom(@PathVariable int id, @RequestBody RoomsRequestDTO data) {
        Rooms rooms = roomsService.updateRoom(data, id);
        return ResponseEntity.ok(rooms);
    }

    @Operation(
            summary = "Delete a Room",
            responses = {
                    @ApiResponse(responseCode = "204",
                            content = @Content(
                                    examples = @ExampleObject(
                                            value = "{}"
                                    )
                            )
                    )
            }
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable int id) {
        roomsService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
