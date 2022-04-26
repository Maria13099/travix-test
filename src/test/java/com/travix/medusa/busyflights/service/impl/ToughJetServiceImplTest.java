package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.TestDataUtils;
import com.travix.medusa.busyflights.converter.ToughJetRequestConverter;
import com.travix.medusa.busyflights.converter.ToughJetResponseMappingFunction;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.rest.RestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ToughJetServiceImplTest {

    @Autowired
    ToughJetServiceImpl toughJetService;
    @MockBean
    RestUtils restUtils;

    @Test
    void testValidToughJetFlights() {
        ToughJetResponse[] toughJetResponses = new ToughJetResponse[1];
        toughJetResponses[0] = TestDataUtils.createToughJetResponse();
        when(restUtils.callToughJetUrl(any())).thenReturn(toughJetResponses);
        BusyFlightsRequest busyFlightsRequest = TestDataUtils.createBusyFlightsRequest();
        List<BusyFlightsResponse> busyFlightsResponseList = toughJetService.getToughJetFlights(busyFlightsRequest);
        assertEquals(1, busyFlightsResponseList.size());
        assertEquals("ToughJet",busyFlightsResponseList.get(0).getSupplier());
    }

    @Test
    void testEmptyToughJetFlights() {
        when(restUtils.callToughJetUrl(any())).thenReturn(new ToughJetResponse[0]);
        BusyFlightsRequest busyFlightsRequest = TestDataUtils.createBusyFlightsRequest();
        List<BusyFlightsResponse> busyFlightsResponseList = toughJetService.getToughJetFlights(busyFlightsRequest);
        assertEquals(0, busyFlightsResponseList.size());
    }
}