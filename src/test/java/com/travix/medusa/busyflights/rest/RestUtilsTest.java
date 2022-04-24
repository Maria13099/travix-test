package com.travix.medusa.busyflights.rest;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RestUtilsTest {

    @Autowired
    public RestUtils restUtils;

    @MockBean
    RestTemplate restTemplate;

    @Test
    void testCallCrazyAirUrl() {
        CrazyAirResponse[] crazyAirResponses = new CrazyAirResponse[2];
        crazyAirResponses[0] = new CrazyAirResponse();
        crazyAirResponses[1] = new CrazyAirResponse();

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), ArgumentMatchers.<Class<CrazyAirResponse[]>>any()))
                .thenReturn(new ResponseEntity<>(crazyAirResponses, HttpStatus.OK));
        assertEquals(2, restUtils.callCrazyAirUrl(new CrazyAirRequest()).length);
    }

    @Test
    void testCallCrazyAirUrlNullResponse() {
        CrazyAirResponse[] crazyAirResponses = new CrazyAirResponse[2];
        crazyAirResponses[0] = new CrazyAirResponse();
        crazyAirResponses[1] = new CrazyAirResponse();

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), ArgumentMatchers.<Class<CrazyAirResponse[]>>any()))
                .thenReturn(null);
        assertNull(restUtils.callCrazyAirUrl(new CrazyAirRequest()));
    }

    @Test
    void testCallToughJetUrl() {
        ToughJetResponse[] toughJetResponse = new ToughJetResponse[2];
        toughJetResponse[0] = new ToughJetResponse();
        toughJetResponse[1] = new ToughJetResponse();

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), ArgumentMatchers.<Class<ToughJetResponse[]>>any()))
                .thenReturn(new ResponseEntity<>(toughJetResponse, HttpStatus.OK));
        assertEquals(2, restUtils.callToughJetUrl(new ToughJetRequest()).length);
    }

    @Test
    void testCallToughJetUrlNullResponse() {
        ToughJetResponse[] toughJetResponse = new ToughJetResponse[2];
        toughJetResponse[0] = new ToughJetResponse();
        toughJetResponse[1] = new ToughJetResponse();

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), ArgumentMatchers.<Class<ToughJetResponse[]>>any()))
                .thenReturn(null);
        assertNull(restUtils.callToughJetUrl(new ToughJetRequest()));
    }
}
