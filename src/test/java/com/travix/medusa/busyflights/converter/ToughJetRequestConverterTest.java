package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.TestDataUtils;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ToughJetRequestConverterTest {

    @Autowired
    ToughJetRequestConverter toughJetRequestConverter;


    @Test
    void testMapBusyFlightReqIntoToughJetReq() {
        ToughJetRequest toughJetRequest = toughJetRequestConverter.convert(TestDataUtils.createBusyFlightsRequest());
        assertEquals("LHR",toughJetRequest.getFrom());
        assertEquals("AMS",toughJetRequest.getTo());
        assertEquals("2022-08-03",toughJetRequest.getOutboundDate());
        assertEquals("2022-08-27",toughJetRequest.getInboundDate());
        assertEquals(4,toughJetRequest.getNumberOfAdults());
    }

}