package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.dto.HealthCheckResponseDTO;
import com.travix.medusa.busyflights.service.HealthCheckService;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckServiceImpl implements HealthCheckService {

    @Override
    public HealthCheckResponseDTO healthCheck() {
        HealthCheckResponseDTO healthResponse = new HealthCheckResponseDTO();
        healthResponse.setResponse("API is up and running");
        healthResponse.setResponseCode(200);

        return healthResponse;
    }

}
