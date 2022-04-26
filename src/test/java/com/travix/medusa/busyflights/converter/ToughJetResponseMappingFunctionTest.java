package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.TestDataUtils;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ToughJetResponseMappingFunctionTest {

    @Autowired
    ToughJetResponseMappingFunction toughJetResponseMappingFunction;

    @Test
    void testMapToughJetResponseIntoBusyFlightResponse() {
        BusyFlightsResponse busyFlightsResponse = toughJetResponseMappingFunction.apply(TestDataUtils.createToughJetResponse());
        assertEquals("ToughJet",busyFlightsResponse.getSupplier());
        assertEquals("British Airways",busyFlightsResponse.getAirline());
        assertEquals(123,busyFlightsResponse.getFare());
        assertEquals("LHR",busyFlightsResponse.getDepartureAirportCode());
        assertEquals("AMS",busyFlightsResponse.getDestinationAirportCode());
        assertEquals("2022-08-03",busyFlightsResponse.getDepartureDate());
        assertEquals("2022-08-27",busyFlightsResponse.getArrivalDate());
    }

}