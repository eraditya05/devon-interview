package com.devon.interview.services;

import com.devon.interview.dtos.FlightBookingInputDto;
import com.devon.interview.dtos.FlightBookingOutputDto;

import java.util.Set;

public interface RouteFlightData {
    void creteFlightDetails();

    Set<String> getRoutes();

    FlightBookingOutputDto createBooking(FlightBookingInputDto flightBookingDto);
}
