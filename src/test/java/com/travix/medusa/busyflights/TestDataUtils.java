package com.travix.medusa.busyflights;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

import java.util.ArrayList;
import java.util.List;

public class TestDataUtils {

    public static BusyFlightsRequest createBusyFlightsRequest() {
        BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest();
        busyFlightsRequest.setOrigin("LHR");
        busyFlightsRequest.setDestination("AMS");
        busyFlightsRequest.setDepartureDate("2022-08-03");
        busyFlightsRequest.setReturnDate("2022-08-27");
        busyFlightsRequest.setNumberOfPassengers(4);
        return busyFlightsRequest;
    }

    public static BusyFlightsRequest createInvalidBusyFlightsRequest() {
        BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest();
        busyFlightsRequest.setOrigin("LHRR");
        busyFlightsRequest.setDestination("MS");
        busyFlightsRequest.setDepartureDate("20220803");
        busyFlightsRequest.setReturnDate("2022-JUN-27");
        busyFlightsRequest.setNumberOfPassengers(5);
        return busyFlightsRequest;
    }

    public static List<BusyFlightsResponse> createBusyFlightsResponse() {
        List<BusyFlightsResponse> busyFlightsResponses = new ArrayList<>();

        BusyFlightsResponse tightAirBusyFlightsResponse = new BusyFlightsResponse();
        tightAirBusyFlightsResponse.setDepartureAirportCode("LHR");
        tightAirBusyFlightsResponse.setDestinationAirportCode("AMS");
        tightAirBusyFlightsResponse.setDepartureDate("2022-08-03");
        tightAirBusyFlightsResponse.setArrivalDate("2022-08-27");
        tightAirBusyFlightsResponse.setSupplier("ToughAir");
        tightAirBusyFlightsResponse.setAirline("EasyJet");
        tightAirBusyFlightsResponse.setFare(101);
        busyFlightsResponses.add(tightAirBusyFlightsResponse);

        BusyFlightsResponse crazyAirBusyFlightsResponse = new BusyFlightsResponse();
        crazyAirBusyFlightsResponse.setDepartureAirportCode("LHR");
        crazyAirBusyFlightsResponse.setDestinationAirportCode("AMS");
        crazyAirBusyFlightsResponse.setDepartureDate("2022-08-03");
        crazyAirBusyFlightsResponse.setArrivalDate("2022-08-27");
        crazyAirBusyFlightsResponse.setSupplier("CrazyAir");
        crazyAirBusyFlightsResponse.setFare(120);
        crazyAirBusyFlightsResponse.setAirline("British Airways");
        busyFlightsResponses.add(crazyAirBusyFlightsResponse);

        BusyFlightsResponse tightAirBusyFlightsResponse2 = new BusyFlightsResponse();
        tightAirBusyFlightsResponse2.setDepartureAirportCode("LHR");
        tightAirBusyFlightsResponse2.setDestinationAirportCode("AMS");
        tightAirBusyFlightsResponse2.setDepartureDate("2022-08-03");
        tightAirBusyFlightsResponse2.setArrivalDate("2022-08-27");
        tightAirBusyFlightsResponse2.setSupplier("ToughAir");
        tightAirBusyFlightsResponse2.setAirline("Lufthansa");
        tightAirBusyFlightsResponse2.setFare(121);
        busyFlightsResponses.add(tightAirBusyFlightsResponse2);

        return busyFlightsResponses;
    }

    public static CrazyAirResponse createCrazyAirResponse() {
        CrazyAirResponse crazyAirResponse = new CrazyAirResponse();
        crazyAirResponse.setAirline("British Airways");
        crazyAirResponse.setPrice(123);
        crazyAirResponse.setDepartureAirportCode("LHR");
        crazyAirResponse.setDestinationAirportCode("AMS");
        crazyAirResponse.setDepartureDate("2022-08-03");
        crazyAirResponse.setArrivalDate("2022-08-27");
        return crazyAirResponse;
    }

    public static ToughJetResponse createToughJetResponse() {
        ToughJetResponse toughJetResponse = new ToughJetResponse();
        toughJetResponse.setCarrier("British Airways");
        toughJetResponse.setBasePrice(123);
        toughJetResponse.setDepartureAirportName("LHR");
        toughJetResponse.setArrivalAirportName("AMS");
        toughJetResponse.setOutboundDateTime("2022-08-03");
        toughJetResponse.setInboundDateTime("2022-08-27");
        return toughJetResponse;
    }
}
