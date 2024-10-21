package com.fidaamahboob.financeapp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.fidaamahboob.financeapp.api.controller.FinanceDataController;
import com.fidaamahboob.financeapp.api.model.FinanceData;
import com.fidaamahboob.financeapp.api.service.FinanceDataService;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(FinanceDataController.class)
public class FinanceDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FinanceDataService financeDataService;

    @InjectMocks
    private FinanceDataController financeDataController;

    private FinanceData data;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
        mockMvc = MockMvcBuilders.standaloneSetup(financeDataController).build(); 
        data = new FinanceData(1L, "17/03/2022", 123, 456, "test");
        
    }

    @Test
    void shouldReturnFinanceDataWhenExists() throws Exception {
        Long existingId = 1L;

        when(financeDataService.getFinanceDataById(existingId)).thenReturn(data);

        mockMvc.perform(get("/finance/v1/data/record/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Expecting 200 OK
                .andExpect(jsonPath("$.id").value(existingId)) // Check ID in response
                .andExpect(jsonPath("$.income").value(123)); // Check income in response

    }

    @Test
    void shouldReturnNotFoundWhenFinanceDataDoesNotExist() throws Exception {
        Long nonExistentId = 99L;

        when(financeDataService.getFinanceDataById(99L)).thenReturn(null);

        mockMvc.perform(get("/finance/v1/data/record/{id}", nonExistentId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestForInvalidId() throws Exception {
        mockMvc.perform(get("/finance/v1/data/record/-1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnEmptyListWhenNoBooks() throws Exception {
        List<FinanceData> empty = List.of();
        when(financeDataService.getAllFinanceData()).thenReturn(empty);

        mockMvc.perform(get("/finance/v1/data/record/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void ShouldAddRecordsSuccessfully() throws Exception {
        String request = objectMapper.writeValueAsString(data);

        when(financeDataService.createFinanceDataRecord(data)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(data));


        mockMvc.perform(post("/finance/v1/data/record/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1)) // Expecting the ID to be 1
                .andExpect(jsonPath("$.income").value(123));
    }
}
