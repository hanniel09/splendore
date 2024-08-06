package com.splendore.services;

import com.splendore.domain.rooms.Rooms;
import com.splendore.domain.rooms.RoomsRequestDTO;
import com.splendore.domain.rooms.RoomsResponseDTO;
import com.splendore.domain.rooms.enums.RoomsLevel;
import com.splendore.domain.rooms.enums.RoomsServices;
import com.splendore.domain.rooms.enums.RoomsStatus;
import com.splendore.exceptions.RoomNotFoundException;
import com.splendore.repositories.RoomsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomsServiceTest {

    @TestConfiguration
    static class RoomsServiceTestContextConfiguration {
        @Bean
        public RoomsService roomsService() {
            return new RoomsService();
        }

    }

    @InjectMocks
    private RoomsService roomsService;

    @Mock
    private RoomsRepository roomsRepository;

    private Rooms rooms;
    private Rooms rooms1;
    private Rooms rooms2;
    private RoomsRequestDTO roomsRequestDTO;
    private RoomsResponseDTO roomsResponseDTO;

    @BeforeEach
    void setUp() {
        rooms1 = new Rooms(1L, RoomsStatus.RESERVED, 1L, 101L, RoomsLevel.MASTER, RoomsServices.ALL, 2L);
        rooms2 = new Rooms(2L, RoomsStatus.AVAILABLE, 2L, 102L, RoomsLevel.DIAMOND, RoomsServices.ALL, 3L);
        roomsRequestDTO = new RoomsRequestDTO(RoomsStatus.RESERVED, 1L, 101L, RoomsLevel.MASTER, RoomsServices.ALL, 2L);
    }

    @Test
    public void testGetAllRooms() {
        when(roomsRepository.findAll()).thenReturn(List.of(rooms1, rooms2));

        List<RoomsResponseDTO> roomsResponseDTOs = roomsService.getAllRooms();

        assertNotNull(roomsResponseDTOs);
        assertEquals(2, roomsResponseDTOs.size());
        RoomsResponseDTO roomsResponseDTO1 = roomsResponseDTOs.get(0);
        RoomsResponseDTO roomsResponseDTO2 = roomsResponseDTOs.get(1);

        assertEquals(rooms1.getId(), roomsResponseDTO1.id());
        assertEquals(rooms1.getRoomsStatus(), roomsResponseDTO1.roomStatus());
        assertEquals(rooms1.getFloorNumber(), roomsResponseDTO1.floorNumber());
        assertEquals(rooms1.getRoomsNumber(), roomsResponseDTO1.roomNumber());
        assertEquals(rooms1.getRoomsLevel(), roomsResponseDTO1.roomsLevel());
        assertEquals(rooms1.getRoomsServices(), roomsResponseDTO1.roomsServices());
        assertEquals(rooms1.getBedsInRoomNumber(), roomsResponseDTO1.bedsInRoomNumber());

        assertEquals(rooms2.getId(), roomsResponseDTO2.id());
        assertEquals(rooms2.getRoomsStatus(), roomsResponseDTO2.roomStatus());
        assertEquals(rooms2.getFloorNumber(), roomsResponseDTO2.floorNumber());
        assertEquals(rooms2.getRoomsNumber(), roomsResponseDTO2.roomNumber());
        assertEquals(rooms2.getRoomsLevel(), roomsResponseDTO2.roomsLevel());
        assertEquals(rooms2.getRoomsServices(), roomsResponseDTO2.roomsServices());
        assertEquals(rooms2.getBedsInRoomNumber(), roomsResponseDTO2.bedsInRoomNumber());

        verify(roomsRepository).findAll();
    }

    @Test
    public void TestCreateRoom() {
        when(roomsRepository.save(any(Rooms.class))).thenReturn(rooms1);

        Rooms createdRoom = roomsService.createRoom(roomsRequestDTO);

        assertNotNull(createdRoom);
        assertEquals(rooms1.getId(), createdRoom.getId());
        assertEquals(rooms1.getRoomsStatus(), createdRoom.getRoomsStatus());
        assertEquals(rooms1.getFloorNumber(), createdRoom.getFloorNumber());
        assertEquals(rooms1.getRoomsNumber(), createdRoom.getRoomsNumber());
        assertEquals(rooms1.getRoomsLevel(), createdRoom.getRoomsLevel());
        assertEquals(rooms1.getRoomsServices(), createdRoom.getRoomsServices());
        assertEquals(rooms1.getBedsInRoomNumber(), createdRoom.getBedsInRoomNumber());

        verify(roomsRepository).save(any(Rooms.class));
    }

    @Test
    public void testGetRoomsById(){
        when(roomsRepository.findById(anyLong())).thenReturn(Optional.of(rooms1));
        Rooms room = roomsService.getRoomById(1L);

        assertNotNull(room);
        assertEquals(rooms1.getId(), room.getId());
        assertEquals(rooms1.getRoomsStatus(), room.getRoomsStatus());
        assertEquals(rooms1.getFloorNumber(), room.getFloorNumber());
        assertEquals(rooms1.getRoomsNumber(), room.getRoomsNumber());
        assertEquals(rooms1.getRoomsLevel(), room.getRoomsLevel());
        assertEquals(rooms1.getRoomsServices(), room.getRoomsServices());
        assertEquals(rooms1.getBedsInRoomNumber(), room.getBedsInRoomNumber());

        verify(roomsRepository).findById(anyLong());
    }

    @Test
    public void testUpdateRoom() {
        when(roomsRepository.findById(anyLong())).thenReturn(Optional.of(rooms1));
        when(roomsRepository.save(any(Rooms.class))).thenReturn(rooms1);

        Rooms updatedRoom = roomsService.updateRoom(roomsRequestDTO, 1L);

        assertNotNull(updatedRoom);
        assertEquals(roomsRequestDTO.roomStatus(), updatedRoom.getRoomsStatus());
        assertEquals(roomsRequestDTO.floorNumber(), updatedRoom.getFloorNumber());
        assertEquals(roomsRequestDTO.roomsNumber(), updatedRoom.getRoomsNumber());
        assertEquals(roomsRequestDTO.roomsLevel(), updatedRoom.getRoomsLevel());
        assertEquals(roomsRequestDTO.roomsServices(), updatedRoom.getRoomsServices());
        assertEquals(roomsRequestDTO.bedsInRoomNumber(), updatedRoom.getBedsInRoomNumber());

        verify(roomsRepository).findById(anyLong());
        verify(roomsRepository).save(any(Rooms.class));
    }

    @Test
    public void testDeleteRoom() {
        when(roomsRepository.existsById(1L)).thenReturn(true);

        // Act
        roomsService.deleteRoom(1L);

        // Assert
        verify(roomsRepository).existsById(1L);
        verify(roomsRepository).deleteById(1L);
    }

    @Test
    public void testDeleteRoomNotFound() {
        when(roomsRepository.existsById(1L)).thenReturn(false);

        assertThrows(RoomNotFoundException.class, () -> {
            roomsService.deleteRoom(1L);
        });

        verify(roomsRepository).existsById(1L);
        verify(roomsRepository, never()).deleteById(1L);
    }
}
