package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.CrazyAirService;
import com.travix.medusa.busyflights.service.ToughJetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class BusyFlightsServiceImplTest {

    @Autowired
    public BusyFlightsServiceImpl busyFlightsService;

    @MockBean
    public CrazyAirService crazyAirService;

    @MockBean
    public ToughJetService toughJetService;

    @Test
    void testNoFlightsReturned() {
        when(crazyAirService.getCrazyAirFlights(null)).thenReturn(new ArrayList<>());
        when(toughJetService.getToughJetFlights(null)).thenReturn(new ArrayList<>());
        assertEquals(0, busyFlightsService.getFlights(null).size());
    }

    @Test
    void testReturnValidFlights() {
        when(crazyAirService.getCrazyAirFlights(any())).thenReturn(createCrazyFlightsResponses());
        when(toughJetService.getToughJetFlights(any())).thenReturn(createToughFlightsResponses());
        List<BusyFlightsResponse> response = busyFlightsService.getFlights(new BusyFlightsRequest());
        assertEquals(5, response.size());
        assertEquals(5, response.get(0).getFare());
        assertEquals(100, response.get(1).getFare());
        assertEquals(422, response.get(2).getFare());
        assertEquals(433, response.get(3).getFare());
        assertEquals(999, response.get(4).getFare());
    }

    private List<BusyFlightsResponse> createToughFlightsResponses() {
        List<BusyFlightsResponse> toughFlights = new ArrayList<>();
        BusyFlightsResponse tightFlightsResponse = new BusyFlightsResponse();
        tightFlightsResponse.setSupplier("CrazyAir");
        tightFlightsResponse.setFare(433);
        toughFlights.add(tightFlightsResponse);
        BusyFlightsResponse tightFlightsResponse2 = new BusyFlightsResponse();
        tightFlightsResponse2.setSupplier("CrazyAir");
        tightFlightsResponse2.setFare(422);
        toughFlights.add(tightFlightsResponse2);
        return toughFlights;
    }

    private List<BusyFlightsResponse> createCrazyFlightsResponses() {
        List<BusyFlightsResponse> crazyFlights = new ArrayList<>();
        BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
        busyFlightsResponse.setSupplier("CrazyAir");
        busyFlightsResponse.setFare(100);
        crazyFlights.add(busyFlightsResponse);
        BusyFlightsResponse busyFlightsResponse2 = new BusyFlightsResponse();
        busyFlightsResponse2.setSupplier("CrazyAir");
        busyFlightsResponse2.setFare(5);
        crazyFlights.add(busyFlightsResponse2);
        BusyFlightsResponse busyFlightsResponse3 = new BusyFlightsResponse();
        busyFlightsResponse3.setSupplier("CrazyAir");
        busyFlightsResponse3.setFare(999);
        crazyFlights.add(busyFlightsResponse3);
        return crazyFlights;
    }
}