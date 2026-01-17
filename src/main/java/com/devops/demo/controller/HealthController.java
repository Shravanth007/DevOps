package com.devops.demo.controller;

import com.devops.demo.model.HealthResponse;
import com.devops.demo.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Health Check Controller
 * Provides endpoints for application health monitoring
 */
@RestController
@RequestMapping("/api")
public class HealthController {

    private final HealthService healthService;

    @Autowired
    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    /**
     * Health check endpoint
     * @return Health status of the application
     */
    @GetMapping("/health")
    public ResponseEntity<HealthResponse> getHealth() {
        HealthResponse response = healthService.checkHealth();
        return ResponseEntity.ok(response);
    }

    /**
     * Version information endpoint
     * @return Application version information
     */
    @GetMapping("/version")
    public ResponseEntity<String> getVersion() {
        return ResponseEntity.ok("DevOps Demo Application v1.0.0");
    }
}
