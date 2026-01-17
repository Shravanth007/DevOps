package com.devops.demo.service;

import com.devops.demo.model.HealthResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Health Service
 * Business logic for health checks and system status
 */
@Service
public class HealthService {

    /**
     * Performs health check of the application
     * @return HealthResponse object with status and timestamp
     */
    public HealthResponse checkHealth() {
        HealthResponse response = new HealthResponse();
        response.setStatus("UP");
        response.setMessage("Application is running smoothly");
        response.setTimestamp(LocalDateTime.now());
        return response;
    }
}
