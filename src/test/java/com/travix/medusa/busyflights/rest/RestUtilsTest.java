package com.travix.medusa.busyflights.rest;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RestUtilsTest {

    @Autowired
    public RestUtils restUtils;

    @MockBean
    RestTemplate restTemplate;

    @Test
    void callCrazyAirUrl() {
        CrazyAirResponse[] crazyAirResponses = new CrazyAirResponse[2];
        crazyAirResponses[0] = new CrazyAirResponse();
        crazyAirResponses[1] = new CrazyAirResponse();

        when(restTemplate.exchange(any(), eq(HttpMethod.GET), any(), ArgumentMatchers.<Class<CrazyAirResponse[]>>any())).
                thenReturn(new ResponseEntity<>(crazyAirResponses, HttpStatus.OK));
        assertEquals(2, restUtils.callCrazyAirUrl(new CrazyAirRequest()).length);
    }

    @Test
    void callCrazyAirUrlNullResponse() {
        CrazyAirResponse[] crazyAirResponses = new CrazyAirResponse[2];
        crazyAirResponses[0] = new CrazyAirResponse();
        crazyAirResponses[1] = new CrazyAirResponse();

        when(restTemplate.exchange(any(), eq(HttpMethod.GET), any(), ArgumentMatchers.<Class<CrazyAirResponse[]>>any())).
                thenReturn(null);
        assertNull(restUtils.callCrazyAirUrl(new CrazyAirRequest()));
    }

    @Test
    void callToughJetUrl() {
        ToughJetResponse[] toughJetResponse = new ToughJetResponse[2];
        toughJetResponse[0] = new ToughJetResponse();
        toughJetResponse[1] = new ToughJetResponse();

        when(restTemplate.exchange(any(), eq(HttpMethod.GET), any(), ArgumentMatchers.<Class<ToughJetResponse[]>>any())).
                thenReturn(new ResponseEntity<>(toughJetResponse, HttpStatus.OK));
        assertEquals(2, restUtils.callToughJetUrl(new ToughJetRequest()).length);
    }

    @Test
    void callToughJetUrlNullResponse() {
        ToughJetResponse[] toughJetResponse = new ToughJetResponse[2];
        toughJetResponse[0] = new ToughJetResponse();
        toughJetResponse[1] = new ToughJetResponse();

        when(restTemplate.exchange(any(), eq(HttpMethod.GET), any(), ArgumentMatchers.<Class<ToughJetResponse[]>>any())).
                thenReturn(null);
        assertNull(restUtils.callToughJetUrl(new ToughJetRequest()));
    }
}
