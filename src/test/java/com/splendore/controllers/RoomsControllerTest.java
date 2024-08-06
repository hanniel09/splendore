package com.splendore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.splendore.configs.TestSecurityConfig;
import com.splendore.domain.rooms.Rooms;
import com.splendore.domain.rooms.RoomsRequestDTO;
import com.splendore.domain.rooms.RoomsResponseDTO;
import com.splendore.domain.rooms.enums.RoomsLevel;
import com.splendore.domain.rooms.enums.RoomsServices;
import com.splendore.domain.rooms.enums.RoomsStatus;
import com.splendore.services.RoomsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = RoomsController.class)
@ContextConfiguration(classes = {TestSecurityConfig.class})
public class RoomsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomsService roomsService;

    @Autowired
    private ObjectMapper objectMapper;


    private Rooms rooms;
    private RoomsRequestDTO roomsRequestDTO;
    private RoomsResponseDTO roomsResponseDTO;

    @BeforeEach
    void setUp() {
        roomsRequestDTO = new RoomsRequestDTO(RoomsStatus.RESERVED, 1L, 101L, RoomsLevel.MASTER, RoomsServices.ALL, 3L);
        roomsResponseDTO = new RoomsResponseDTO(1L, RoomsStatus.RESERVED, 1L, 101L, RoomsLevel.MASTER, RoomsServices.ALL, 3L);
        rooms = new Rooms(2L, RoomsStatus.RESERVED, 1L, 101L, RoomsLevel.MASTER, RoomsServices.ALL, 3L);
    }

    @Test
    public void testGetAllRooms() throws Exception {
        List<RoomsResponseDTO> roomsList = List.of(roomsResponseDTO);

        when(roomsService.getAllRooms()).thenReturn(roomsList);


        mockMvc.perform(MockMvcRequestBuilders.get("/rooms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(roomsResponseDTO.id()))
                .andExpect(jsonPath("$[0].roomStatus").value(roomsResponseDTO.roomStatus().toString()))
                .andExpect(jsonPath("$[0].floorNumber").value(roomsResponseDTO.floorNumber()))
                .andExpect(jsonPath("$[0].roomNumber").value(roomsResponseDTO.roomNumber()))
                .andExpect(jsonPath("$[0].roomsLevel").value(roomsResponseDTO.roomsLevel().toString()))
                .andExpect(jsonPath("$[0].roomsServices").value(roomsResponseDTO.roomsServices().toString()))
                .andExpect(jsonPath("$[0].bedsInRoomNumber").value(roomsResponseDTO.bedsInRoomNumber()));
    }

    @Test
    @WithMockUser
    public void testGetRoomById() throws Exception {
        when(roomsService.getRoomById(2L)).thenReturn(rooms);

        mockMvc.perform(MockMvcRequestBuilders.get("/rooms/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(rooms.getId()))
                .andExpect(jsonPath("$.roomsStatus").value(rooms.getRoomsStatus().toString()))
                .andExpect(jsonPath("$.floorNumber").value(rooms.getFloorNumber()))
                .andExpect(jsonPath("$.roomsNumber").value(rooms.getRoomsNumber()))
                .andExpect(jsonPath("$.roomsLevel").value(rooms.getRoomsLevel().toString()))
                .andExpect(jsonPath("$.roomsServices").value(rooms.getRoomsServices().toString()))
                .andExpect(jsonPath("$.bedsInRoomNumber").value(rooms.getBedsInRoomNumber()));
    }

    @Test
    @WithMockUser
    public void testCreateRoom() throws Exception {
        when(roomsService.createRoom(any(RoomsRequestDTO.class))).thenReturn(rooms);

        mockMvc.perform(MockMvcRequestBuilders.post("/rooms")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(roomsRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(rooms.getId()))
                .andExpect(jsonPath("$.roomsStatus").value(rooms.getRoomsStatus().toString()))
                .andExpect(jsonPath("$.floorNumber").value(rooms.getFloorNumber()))
                .andExpect(jsonPath("$.roomsNumber").value(rooms.getRoomsNumber()))
                .andExpect(jsonPath("$.roomsLevel").value(rooms.getRoomsLevel().toString()))
                .andExpect(jsonPath("$.roomsServices").value(rooms.getRoomsServices().toString()))
                .andExpect(jsonPath("$.bedsInRoomNumber").value(rooms.getBedsInRoomNumber()));
    }

    @Test
    @WithMockUser
    public void testUpdateRoom() throws Exception {
        when(roomsService.updateRoom(any(RoomsRequestDTO.class), anyLong())).thenReturn(rooms);

        mockMvc.perform(MockMvcRequestBuilders.put("/rooms/2")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(roomsRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(rooms.getId()))
                .andExpect(jsonPath("$.roomsStatus").value(rooms.getRoomsStatus().toString()))
                .andExpect(jsonPath("$.floorNumber").value(rooms.getFloorNumber()))
                .andExpect(jsonPath("$.roomsNumber").value(rooms.getRoomsNumber()))
                .andExpect(jsonPath("$.roomsLevel").value(rooms.getRoomsLevel().toString()))
                .andExpect(jsonPath("$.roomsServices").value(rooms.getRoomsServices().toString()))
                .andExpect(jsonPath("$.bedsInRoomNumber").value(rooms.getBedsInRoomNumber()));
    }

    @Test
    @WithMockUser
    public void testDeleteRoom() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/rooms/2"))
                .andExpect(status().isNoContent());

        verify(roomsService).deleteRoom(2L);
    }

}
