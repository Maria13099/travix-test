package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.converter.CrazyAirRequestConverter;
import com.travix.medusa.busyflights.converter.CrazyAirResponseMappingFunction;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
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

    private final CrazyAirRequestConverter crazyAirRequestConverter;

    private final CrazyAirResponseMappingFunction crazyAirResponseMappingFunction;

    public CrazyAirServiceImpl(RestUtils restUtils, CrazyAirRequestConverter crazyAirRequestConverter, CrazyAirResponseMappingFunction crazyAirResponseMappingFunction) {
        this.restUtils = restUtils;
        this.crazyAirRequestConverter = crazyAirRequestConverter;
        this.crazyAirResponseMappingFunction = crazyAirResponseMappingFunction;
    }

    @Override
    public List<BusyFlightsResponse> getCrazyAirFlights(BusyFlightsRequest busyFlightsRequest) {
        CrazyAirResponse[] crazyAirResponse = restUtils.callCrazyAirUrl(crazyAirRequestConverter.convert(busyFlightsRequest));
        if (logger.isDebugEnabled()) {
            logger.debug("Received CrazyAirResponse array ]" + crazyAirResponse + "[");
        }

        if (crazyAirResponse == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(crazyAirResponse).map(crazyAirResponseMappingFunction).collect(Collectors.toList());
    }
}
