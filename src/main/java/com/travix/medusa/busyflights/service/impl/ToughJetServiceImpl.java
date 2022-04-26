package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.converter.ToughJetRequestConverter;
import com.travix.medusa.busyflights.converter.ToughJetResponseMappingFunction;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
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

    private final ToughJetRequestConverter toughJetRequestConverter;

    private final ToughJetResponseMappingFunction toughJetResponseMappingFunction;

    public ToughJetServiceImpl(RestUtils restUtils, ToughJetRequestConverter toughJetRequestConverter, ToughJetResponseMappingFunction toughJetResponseMappingFunction) {
        this.restUtils = restUtils;
        this.toughJetRequestConverter = toughJetRequestConverter;
        this.toughJetResponseMappingFunction = toughJetResponseMappingFunction;
    }

    @Override
    public List<BusyFlightsResponse> getToughJetFlights(BusyFlightsRequest busyFlightsRequest) {
        ToughJetResponse[] toughJetResponse = restUtils.callToughJetUrl(toughJetRequestConverter.convert(busyFlightsRequest));
        if (logger.isDebugEnabled()) {
            logger.debug("Received ToughJetResponse array ]" + toughJetResponse + "[");
        }

        if (toughJetResponse == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(toughJetResponse).map(toughJetResponseMappingFunction).collect(Collectors.toList());
    }
}
