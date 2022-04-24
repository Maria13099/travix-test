package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.rest.RestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class CrazyAirServiceImplTest {

    @Autowired
    public CrazyAirServiceImpl crazyAirService;

    @MockBean
    RestUtils restUtils;

    @Test
    void getCrazyAirFlights() {
        BusyFlightsRequest busyFlightsRequest = createBusyFlightsRequest();
        crazyAirService.getCrazyAirFlights(busyFlightsRequest);
    }

    private BusyFlightsRequest createBusyFlightsRequest() {
        BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest();
        busyFlightsRequest.setOrigin("London");
        busyFlightsRequest.setDestination("New York");
        busyFlightsRequest.setDepartureDate("20220803");
        busyFlightsRequest.setReturnDate("20220827");
        busyFlightsRequest.setNumberOfPassengers(4);
        return busyFlightsRequest;
    }

    @Test
    void mapBusyFlightReqIntoCrazyAirReq() {
    }

    @Test
    void mapCrazyAirResponseIntoBusyFlightResponse() {
    }
}