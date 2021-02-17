package com.devon.interview.controllers;


import com.devon.interview.dtos.FlightBookingInputDto;
import com.devon.interview.dtos.FlightBookingOutputDto;
import com.devon.interview.services.FlightServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/booking")
public class BookingController {


    @Autowired
    private FlightServices flightServices;


    @PostMapping
    public FlightBookingOutputDto createBooking(@RequestBody @Validated FlightBookingInputDto flightBookingDto) {
        return flightServices.createBooking(flightBookingDto);

    }

    @GetMapping("{pnrNo}")
    public FlightBookingOutputDto getBooking(@PathVariable String pnrNo) {
        return flightServices.getBooking(pnrNo);

    }

    @PutMapping
    public FlightBookingOutputDto updateBooking(@RequestBody @Validated FlightBookingInputDto flightBookingDto) {
        return flightServices.updateBooking(flightBookingDto);
    }


}
