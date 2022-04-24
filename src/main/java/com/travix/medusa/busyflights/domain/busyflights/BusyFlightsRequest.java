package com.travix.medusa.busyflights.domain.busyflights;

import com.travix.medusa.busyflights.validation.CheckDateFormat;

import javax.validation.constraints.*;

public class BusyFlightsRequest {

    @NotNull(message = "Origin cannot be null")
    @NotBlank(message = "Origin cannot be blank")
    private String origin;

    @NotNull(message = "Destination cannot be null")
    @NotBlank(message = "Destination cannot be blank")
    private String destination;

    @NotNull(message = "Departure date cannot be null")
    @NotBlank(message = "Departure date cannot be blank")
    @CheckDateFormat(pattern = "yyyyMMdd")
    private String departureDate;

    @NotNull(message = "Return date cannot be null")
    @NotBlank(message = "Return date cannot be blank")
    @CheckDateFormat(pattern = "yyyyMMdd")
    private String returnDate;

    @Max(value = 4, message = "Maximum number of passengers should not be grater than 4")
    @Min(value = 1, message = "Minimum number of passengers should not be less than 1")
    private int numberOfPassengers;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(final String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(final String returnDate) {
        this.returnDate = returnDate;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(final int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
}
