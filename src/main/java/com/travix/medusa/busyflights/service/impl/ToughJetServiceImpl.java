package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.rest.RestUtils;
import com.travix.medusa.busyflights.service.ToughJetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToughJetServiceImpl implements ToughJetService {

    private static final Logger logger = LoggerFactory.getLogger(ToughJetServiceImpl.class);

    private final RestUtils restUtils;

    public ToughJetServiceImpl(RestUtils restUtils) {
        this.restUtils = restUtils;
    }

    @Override
    public List<BusyFlightsResponse> getToughJetFlights(BusyFlightsRequest busyFlightsRequest) {
        ToughJetResponse[] toughJetResponse = restUtils.callToughJetUrl(mapBusyFlightReqIntoToughJetReq(busyFlightsRequest));
        if (logger.isDebugEnabled()) {
            logger.debug("Received ToughJetResponse array ]" + toughJetResponse + "[");
        }

        if (toughJetResponse == null)
        {
            return new ArrayList<>();
        }
        return Arrays.stream(toughJetResponse).map(response -> mapToughJetResponseIntoBusyFlightResponse(response)).collect(Collectors.toList());
    }

    public ToughJetRequest mapBusyFlightReqIntoToughJetReq(BusyFlightsRequest busyFlightsRequest) {
        ToughJetRequest toughJetRequest = new ToughJetRequest();
        toughJetRequest.setFrom(busyFlightsRequest.getOrigin());
        toughJetRequest.setTo(busyFlightsRequest.getDestination());
        toughJetRequest.setOutboundDate(busyFlightsRequest.getDestination());
        toughJetRequest.setInboundDate(busyFlightsRequest.getReturnDate());
        toughJetRequest.setNumberOfAdults(busyFlightsRequest.getNumberOfPassengers());
        return toughJetRequest;
    }

    public BusyFlightsResponse mapToughJetResponseIntoBusyFlightResponse(ToughJetResponse toughJetResponse) {
        BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
        busyFlightsResponse.setAirline(toughJetResponse.getCarrier());
        busyFlightsResponse.setSupplier("ToughJet");
        busyFlightsResponse.setFare(toughJetResponse.getBasePrice());
        busyFlightsResponse.setDepartureAirportCode(toughJetResponse.getDepartureAirportName());
        busyFlightsResponse.setDestinationAirportCode(toughJetResponse.getArrivalAirportName());
        busyFlightsResponse.setDepartureDate(toughJetResponse.getOutboundDateTime());
        busyFlightsResponse.setArrivalDate(toughJetResponse.getInboundDateTime());
        return busyFlightsResponse;
    }
}
