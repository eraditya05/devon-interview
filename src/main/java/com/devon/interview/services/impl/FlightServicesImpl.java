package com.devon.interview.services.impl;

import com.devon.interview.dtos.FlightBookingInputDto;
import com.devon.interview.dtos.FlightBookingOutputDto;
import com.devon.interview.services.FlightServices;
import com.devon.interview.services.RouteFlightData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class FlightServicesImpl implements FlightServices {

    @Autowired
    private RouteFlightData routeFlightData;

    @Override
    public FlightBookingOutputDto createBooking(FlightBookingInputDto flightBookingDto) {
        return routeFlightData.createBooking(flightBookingDto);
    }

    @Override
    public FlightBookingOutputDto getBooking(String pnrNo) {
        return null;
    }

    @Override
    public FlightBookingOutputDto updateBooking(FlightBookingInputDto flightBookingDto) {
        return null;
    }

}
