package com.travix.medusa.busyflights.domain.busyflights;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BusyFlightsResponse {

    private String airline;

    private String supplier;

    private double fare;

    private String departureAirportCode;

    private String destinationAirportCode;

    private String departureDate;

    private String arrivalDate;

}
