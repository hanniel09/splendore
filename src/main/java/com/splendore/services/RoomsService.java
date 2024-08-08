package com.splendore.services;

import com.splendore.domain.rooms.Rooms;
import com.splendore.domain.rooms.RoomsRequestDTO;
import com.splendore.domain.rooms.RoomsResponseDTO;
import com.splendore.exceptions.NotFoundException;
import com.splendore.repositories.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomsService {

    @Autowired
    private RoomsRepository roomsRepository;

    public List<RoomsResponseDTO> getAllRooms() {
        List<Rooms> rooms = this.roomsRepository.findAll();
        return rooms.stream()
                .map(room -> new RoomsResponseDTO(
                        room.getId(),
                        room.getRoomsStatus(),
                        room.getFloorNumber(),
                        room.getRoomsNumber(),
                        room.getRoomsLevel(),
                        room.getRoomsServices(),
                        room.getBedsInRoomNumber()
                ))
                .collect(Collectors.toList());
    }

    public Rooms getRoomById(long id) {
        return this.roomsRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Room not found with id: " + id)
        );
    }

    public List<Rooms> getRoomsByIds(List<Long> roomIds) {
        return roomIds.stream()
                .map(roomsRepository::findById)
                .map(optionalRoom -> optionalRoom.orElseThrow(() -> new NotFoundException("Room not found")))
                .collect(Collectors.toList());
    }

    public Rooms createRoom(RoomsRequestDTO roomsRequestDTO) {
        Rooms room = new Rooms();
        room.setRoomsStatus(roomsRequestDTO.roomStatus());
        room.setFloorNumber(roomsRequestDTO.floorNumber());
        room.setRoomsNumber(roomsRequestDTO.roomsNumber());
        room.setRoomsLevel(roomsRequestDTO.roomsLevel());
        room.setRoomsServices(roomsRequestDTO.roomsServices());
        room.setBedsInRoomNumber(roomsRequestDTO.bedsInRoomNumber());

        return this.roomsRepository.save(room);
    }

    public Rooms updateRoom(RoomsRequestDTO roomsRequestDTO, long id) {
        Rooms newRoom = new Rooms(roomsRequestDTO);
        Rooms updateRoom = this.getRoomById(id);
        newRoom.setId(updateRoom.getId());
        return this.roomsRepository.save(newRoom);
    }

    public void deleteRoom(long id) {
        if (!roomsRepository.existsById(id)) {
            throw new NotFoundException("Room not found with id: " + id);
        }
        roomsRepository.deleteById(id);
    }
}
