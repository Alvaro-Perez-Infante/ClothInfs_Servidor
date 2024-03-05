package com.truongbn.security.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.ClothInfs.controller.RopaController;
import com.example.ClothInfs.exception.ResourceNotFoundException;
import com.example.ClothInfs.model.Ropa;
import com.example.ClothInfs.service.RopaService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RopaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RopaService ropaService;

    @InjectMocks
    private RopaController ropaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ropaController).build();
    }

    @Test
    void testGetAllRopa() throws Exception {
        Ropa ropa1 = new Ropa();
        ropa1.setId(1L);
        Ropa ropa2 = new Ropa();
        ropa2.setId(2L);
        List<Ropa> ropas = Arrays.asList(ropa1, ropa2);
        
        doReturn(ropas).when(ropaService).getAllRopa();
        
        mockMvc.perform(get("/api/ropa"))
            .andExpect(status().isOk());
        
        verify(ropaService).getAllRopa();
    }

    @Test
    void testGetRopaById() throws Exception {
        Ropa ropa = new Ropa();
        ropa.setId(1L);
        
        doReturn(ropa).when(ropaService).getRopaById(1L);
        
        mockMvc.perform(get("/api/ropa/1"))
            .andExpect(status().isOk());
        
        verify(ropaService).getRopaById(1L);
    }

    @Test
    void testGetRopaById_NotFound() throws Exception {
        doReturn(null).when(ropaService).getRopaById(1L);
        
        mockMvc.perform(get("/api/ropa/1"))
            .andExpect(status().isNotFound());
        
        verify(ropaService).getRopaById(1L);
    }

    @Test
    void testAddRopa() throws Exception {
        Ropa ropa = new Ropa();
        ropa.setId(1L);
        
        doReturn(ropa).when(ropaService).addRopa(any());
        
        mockMvc.perform(post("/api/ropa")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(ropa)))
            .andExpect(status().isCreated());
        
        verify(ropaService).addRopa(any());
    }

    @Test
    void testUpdateRopa() throws Exception {
        Ropa ropa = new Ropa();
        ropa.setId(1L);
        
        doReturn(ropa).when(ropaService).updateRopa(1L, ropa);
        
        mockMvc.perform(put("/api/ropa/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(ropa)))
            .andExpect(status().isOk());
        
        verify(ropaService).updateRopa(1L, ropa);
    }

    @Test
    void testUpdateRopa_NotFound() throws Exception {
        Ropa ropa = new Ropa();
        ropa.setId(1L);
        
        doReturn(null).when(ropaService).updateRopa(1L, ropa);
        
        mockMvc.perform(put("/api/ropa/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(ropa)))
            .andExpect(status().isNotFound());
        
        verify(ropaService).updateRopa(1L, ropa);
    }

    @Test
    void testDeleteRopa() throws Exception {
        doNothing().when(ropaService).deleteRopa(1L);
        
        mockMvc.perform(delete("/api/ropa/1"))
            .andExpect(status().isNoContent());
        
        verify(ropaService).deleteRopa(1L);
    }
}
