package com.travix.medusa.busyflights.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName(value = "HealthCheckResponse")
public class HealthCheckResponseDTO {
    @JsonProperty(required = true, value = "responseCode")
    private int responseCode;
    @JsonProperty(required = true, value = "response")
    private String response;
}
