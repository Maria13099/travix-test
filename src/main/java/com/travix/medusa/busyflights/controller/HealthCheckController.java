package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.dto.HealthCheckResponseDTO;
import com.travix.medusa.busyflights.service.HealthCheckService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "/ticket",
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class HealthCheckController {

    public final HealthCheckService healthCheckService;

    public HealthCheckController(HealthCheckService service) {
        this.healthCheckService = service;
    }

    @GetMapping("/healthcheck")
    public HealthCheckResponseDTO healthCheck() {

        return healthCheckService.healthCheck();
    }
}
