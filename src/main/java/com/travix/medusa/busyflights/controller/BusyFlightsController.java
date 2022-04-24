package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(
        value = "/",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
public class BusyFlightsController {

    private static Logger logger = LoggerFactory.getLogger(BusyFlightsController.class);

    private final BusyFlightsService busyFlightsService;

    public BusyFlightsController(BusyFlightsService busyFlightsService) {
        this.busyFlightsService = busyFlightsService;
    }

//    @ExceptionHandler({ResourceNotFoundException.class})
//    public void handleException() {
//        //
//    }

    @GetMapping("/flights")
    public ResponseEntity<List<BusyFlightsResponse>> getFlights(
            @Valid @RequestBody BusyFlightsRequest busyFlightsRequest) {
        if (logger.isDebugEnabled()) {
            logger.debug("Received BusyFlightsRequest ]" + busyFlightsRequest + "[");
        }

        List<BusyFlightsResponse> busyFlightsResponse = busyFlightsService.getFlights(busyFlightsRequest);
        if (busyFlightsResponse.size() == 0){
            return new ResponseEntity<>(busyFlightsResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(busyFlightsResponse, HttpStatus.OK);

    }
}


