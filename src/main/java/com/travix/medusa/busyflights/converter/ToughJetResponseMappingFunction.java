package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

@Controller
public class ToughJetResponseMappingFunction implements Function<ToughJetResponse, BusyFlightsResponse> {

    @Override
    public BusyFlightsResponse apply(ToughJetResponse toughJetResponse) {
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
