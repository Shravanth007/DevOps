package com.devops.demo.service;

import com.devops.demo.model.HealthResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HealthServiceTest {

    private HealthService healthService;

    @BeforeEach
    void setUp() {
        healthService = new HealthService();
    }

    @Test
    void testCheckHealth() {
        // Act
        HealthResponse response = healthService.checkHealth();

        // Assert
        assertNotNull(response);
        assertEquals("UP", response.getStatus());
        assertEquals("Application is running smoothly", response.getMessage());
        assertNotNull(response.getTimestamp());
    }
}
