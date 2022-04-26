package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.TestDataUtils;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CrazyAirRequestConverterTest {

    @Autowired
    public CrazyAirRequestConverter crazyAirRequestConverter;

    @Test
    void testMapBusyFlightReqIntoCrazyAirReq() {
        CrazyAirRequest crazyAirRequest = crazyAirRequestConverter.convert(TestDataUtils.createBusyFlightsRequest());
        assertEquals("LHR",crazyAirRequest.getOrigin());
        assertEquals("AMS",crazyAirRequest.getDestination());
        assertEquals("2022-08-03",crazyAirRequest.getDepartureDate());
        assertEquals("2022-08-27",crazyAirRequest.getReturnDate());
        assertEquals(4,crazyAirRequest.getPassengerCount());
    }
}