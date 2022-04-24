package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.rest.RestUtils;
import com.travix.medusa.busyflights.service.CrazyAirService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrazyAirServiceImpl implements CrazyAirService {

    private static final Logger logger = LoggerFactory.getLogger(CrazyAirServiceImpl.class);

    private final RestUtils restUtils;

    public CrazyAirServiceImpl(RestUtils restUtils) {
        this.restUtils = restUtils;
    }

    @Override
    public List<BusyFlightsResponse> getCrazyAirFlights(BusyFlightsRequest busyFlightsRequest) {
        CrazyAirResponse[] crazyAirResponse = restUtils.callCrazyAirUrl(mapBusyFlightReqIntoCrazyAirReq(busyFlightsRequest));
        if (logger.isDebugEnabled()) {
            logger.debug("Received CrazyAirResponse array ]" + crazyAirResponse + "[");
        }

        if (crazyAirResponse == null)
        {
            return new ArrayList<>();
        }
        return Arrays.stream(crazyAirResponse).map(response -> mapCrazyAirResponseIntoBusyFlightResponse(response)).collect(Collectors.toList());
    }

    public CrazyAirRequest mapBusyFlightReqIntoCrazyAirReq(BusyFlightsRequest busyFlightsRequest) {
        CrazyAirRequest crazyAirRequest = new CrazyAirRequest();
        crazyAirRequest.setOrigin(busyFlightsRequest.getOrigin());
        crazyAirRequest.setDestination(busyFlightsRequest.getDestination());
        crazyAirRequest.setDepartureDate(busyFlightsRequest.getDepartureDate());
        crazyAirRequest.setReturnDate(busyFlightsRequest.getReturnDate());
        crazyAirRequest.setPassengerCount(busyFlightsRequest.getNumberOfPassengers());
        return crazyAirRequest;
    }

    public BusyFlightsResponse mapCrazyAirResponseIntoBusyFlightResponse(CrazyAirResponse crazyAirResponse) {
        BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
        busyFlightsResponse.setAirline(crazyAirResponse.getAirline());
        busyFlightsResponse.setSupplier("CrazyAir");
        busyFlightsResponse.setFare(crazyAirResponse.getPrice());
        busyFlightsResponse.setDepartureAirportCode(crazyAirResponse.getDepartureAirportCode());
        busyFlightsResponse.setDestinationAirportCode(crazyAirResponse.getDestinationAirportCode());
        busyFlightsResponse.setDepartureDate(crazyAirResponse.getDepartureDate());
        busyFlightsResponse.setArrivalDate(crazyAirResponse.getArrivalDate());
        return busyFlightsResponse;
    }
}
