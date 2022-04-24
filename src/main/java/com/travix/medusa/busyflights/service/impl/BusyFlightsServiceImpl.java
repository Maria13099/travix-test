package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightsService;
import com.travix.medusa.busyflights.service.CrazyAirService;
import com.travix.medusa.busyflights.service.ToughJetService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusyFlightsServiceImpl implements BusyFlightsService {

    public CrazyAirService crazyAirService;

    public ToughJetService toughJetService;

    public BusyFlightsServiceImpl(CrazyAirService crazyAirService, ToughJetService toughJetService) {
        this.crazyAirService = crazyAirService;
        this.toughJetService = toughJetService;
    }

    @Override
    public List<BusyFlightsResponse> getFlights(BusyFlightsRequest busyFlightsRequest) {
        List<BusyFlightsResponse> listOfFlights = new ArrayList<>();
        listOfFlights.addAll(crazyAirService.getCrazyAirFlights(busyFlightsRequest));
        listOfFlights.addAll(toughJetService.getToughJetFlights(busyFlightsRequest));
        return listOfFlights.stream().sorted(Comparator.comparing(flight -> flight.getFare())).collect(Collectors.toList());
    }
}
