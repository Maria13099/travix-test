package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.TestDataUtils;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.rest.RestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CrazyAirServiceImplTest {

    @Autowired
    public CrazyAirServiceImpl crazyAirService;

    @MockBean
    RestUtils restUtils;

    @Test
    void testValidCrazyAirFlights() {
        CrazyAirResponse[] crazyAirResponses = new CrazyAirResponse[1];
        crazyAirResponses[0] = TestDataUtils.createCrazyAirResponse();
        when(restUtils.callCrazyAirUrl(any())).thenReturn(crazyAirResponses);
        BusyFlightsRequest busyFlightsRequest = TestDataUtils.createBusyFlightsRequest();
        List<BusyFlightsResponse> busyFlightsResponseList = crazyAirService.getCrazyAirFlights(busyFlightsRequest);
        assertEquals(1, busyFlightsResponseList.size());
        assertEquals("CrazyAir",busyFlightsResponseList.get(0).getSupplier());
    }

    @Test
    void testEmptyCrazyAirFlights() {
        when(restUtils.callCrazyAirUrl(any())).thenReturn(new CrazyAirResponse[0]);
        BusyFlightsRequest busyFlightsRequest = TestDataUtils.createBusyFlightsRequest();
        List<BusyFlightsResponse> busyFlightsResponseList = crazyAirService.getCrazyAirFlights(busyFlightsRequest);
        assertEquals(0, busyFlightsResponseList.size());
    }

    @Test
    void testMapBusyFlightReqIntoCrazyAirReq() {
        CrazyAirRequest crazyAirRequest = crazyAirService.mapBusyFlightReqIntoCrazyAirReq(TestDataUtils.createBusyFlightsRequest());
        assertEquals("LHR",crazyAirRequest.getOrigin());
        assertEquals("AMS",crazyAirRequest.getDestination());
        assertEquals("2022-08-03",crazyAirRequest.getDepartureDate());
        assertEquals("2022-08-27",crazyAirRequest.getReturnDate());
        assertEquals(4,crazyAirRequest.getPassengerCount());
    }

    @Test
    void testMapCrazyAirResponseIntoBusyFlightResponse() {
        BusyFlightsResponse busyFlightsResponse = crazyAirService.mapCrazyAirResponseIntoBusyFlightResponse(TestDataUtils.createCrazyAirResponse());
        assertEquals("CrazyAir",busyFlightsResponse.getSupplier());
        assertEquals("British Airways",busyFlightsResponse.getAirline());
        assertEquals(123,busyFlightsResponse.getFare());
        assertEquals("LHR",busyFlightsResponse.getDepartureAirportCode());
        assertEquals("AMS",busyFlightsResponse.getDestinationAirportCode());
        assertEquals("2022-08-03",busyFlightsResponse.getDepartureDate());
        assertEquals("2022-08-27",busyFlightsResponse.getArrivalDate());
    }
}