package com.devops.demo.controller;

import com.devops.demo.model.HealthResponse;
import com.devops.demo.service.HealthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class HealthControllerTest {

    @Mock
    private HealthService healthService;

    @InjectMocks
    private HealthController healthController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHealth() {
        // Arrange
        HealthResponse mockResponse = new HealthResponse("UP", "Service is healthy", LocalDateTime.now());
        when(healthService.checkHealth()).thenReturn(mockResponse);

        // Act
        ResponseEntity<HealthResponse> response = healthController.getHealth();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("UP", response.getBody().getStatus());
    }

    @Test
    void testGetVersion() {
        // Act
        ResponseEntity<String> response = healthController.getVersion();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("DevOps Demo Application v1.0.0", response.getBody());
    }
}
