package com.travix.medusa.busyflights.rest;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class RestUtils {

    private static final Logger logger = LoggerFactory.getLogger(RestUtils.class);

    private RestTemplate restTemplate;

    @Value("${crazyair.endpoint}")
    private String crazyAirUrl;

    @Value("${toughjet.endpoint}")
    private String toughJetUrl;

    public RestUtils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CrazyAirResponse[] callCrazyAirUrl(CrazyAirRequest crazyAirRequest) {
        if (logger.isDebugEnabled()) {
            logger.debug("Making call request for " + crazyAirUrl);
        }

        ResponseEntity<CrazyAirResponse[]> responseEntity = restTemplate.exchange(crazyAirUrl,
                HttpMethod.GET, new HttpEntity<>(crazyAirRequest), CrazyAirResponse[].class);
        if (responseEntity == null) {
            logger.error("Invalid response from " + crazyAirUrl);
            return null;
        }

        CrazyAirResponse[] response = responseEntity.getBody();
        if (logger.isDebugEnabled()) {
            logger.debug("Request response " + response);
        }
        return response;
    }

    public ToughJetResponse[] callToughJetUrl(ToughJetRequest toughJetRequest) {
        if (logger.isDebugEnabled()) {
            logger.debug("Making call request for " + toughJetUrl);
        }
        ResponseEntity<ToughJetResponse[]> responseEntity = restTemplate.exchange(toughJetUrl, HttpMethod.GET, new HttpEntity<>(toughJetRequest), ToughJetResponse[].class);
        if (responseEntity == null) {
            logger.error("Invalid response from " + toughJetUrl);
            return null;
        }

        ToughJetResponse[] response = responseEntity.getBody();
        if (logger.isDebugEnabled()) {
            logger.debug("Request response " + response);
        }
        return response;
    }
}
