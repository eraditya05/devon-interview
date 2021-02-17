package com.devon.interview.services;

import com.devon.interview.dtos.FlightBookingInputDto;
import com.devon.interview.dtos.FlightBookingOutputDto;

public interface FlightServices {
    FlightBookingOutputDto createBooking(FlightBookingInputDto flightBookingDto);

    FlightBookingOutputDto getBooking(String pnrNo);

    FlightBookingOutputDto updateBooking(FlightBookingInputDto flightBookingDto);
}
