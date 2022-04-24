package com.travix.medusa.busyflights.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.TestDataUtils;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.service.BusyFlightsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BusyFlightsControllerTest {

    @MockBean
    public BusyFlightsService busyFlightsService;

    @Autowired
    MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testValidFlights() throws Exception {
        when(busyFlightsService.getFlights(any())).thenReturn(TestDataUtils.createBusyFlightsResponse());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestDataUtils.createBusyFlightsRequest()))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.length()", is(3)))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].airline", is("EasyJet")))
                .andExpect(jsonPath("$[0].supplier", is("ToughAir")))
                .andExpect(jsonPath("$[0].fare", is(101.0)))
                .andExpect(jsonPath("$[0].departureAirportCode", is("LHR")))
                .andExpect(jsonPath("$[0].destinationAirportCode", is("AMS")))
                .andExpect(jsonPath("$[0].departureDate", is("2022-08-03")))
                .andExpect(jsonPath("$[0].arrivalDate", is("2022-08-27")));
    }

    @Test
    void testEmptyFlights() throws Exception {
        when(busyFlightsService.getFlights(any())).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestDataUtils.createBusyFlightsRequest()))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.length()", is(0)))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testInvalidRequestBody() {
        BusyFlightsRequest busyFlightsRequest = TestDataUtils.createInvalidBusyFlightsRequest();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<BusyFlightsRequest>> constraintViolations =
                validator.validate(busyFlightsRequest);

        assertEquals(5, constraintViolations.size());
    }
}