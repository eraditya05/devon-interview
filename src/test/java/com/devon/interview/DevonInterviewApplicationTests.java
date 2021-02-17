package com.devon.interview;

import com.devon.interview.dtos.FlightBookingInputDto;
import com.devon.interview.dtos.FlightBookingOutputDto;
import com.devon.interview.dtos.Traveller;
import com.devon.interview.services.impl.RouteFlightDataImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DevonInterviewApplicationTests {

    @InjectMocks
    private RouteFlightDataImpl routeFlightData;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreateBooking() {
        FlightBookingInputDto flightBookingInputDto = new FlightBookingInputDto();
        flightBookingInputDto.setDestination("Test");
        flightBookingInputDto.setSource("Test1");
        Date doj = new Date();
        flightBookingInputDto.setDoj(doj);
        flightBookingInputDto.setRoute("route1");
        List<Traveller> travellers = new ArrayList<>();
        Traveller traveller = new Traveller();
        traveller.setDoj(doj);
        Date dob = Date.from(LocalDate.of(1988, Month.NOVEMBER, 05)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant());
        traveller.setDob(dob);
        traveller.setFName("Aditya");
        traveller.setLName("Singh");
        travellers.add(traveller);
        flightBookingInputDto.setTravellers(travellers);

        FlightBookingOutputDto booking = routeFlightData.createBooking(flightBookingInputDto);
        assertNotNull(booking);
        assertEquals(booking.getSeats().get(0), "1A");

        Traveller traveller1 = new Traveller();
        traveller.setDoj(doj);
        traveller.setDob(dob);
        traveller.setFName("Aditya");
        traveller.setLName("Singh");
        travellers.add(traveller1);
        Traveller traveller2 = new Traveller();
        traveller.setDoj(doj);
        traveller.setDob(dob);
        traveller.setFName("Aditya");
        traveller.setLName("Singh");
        travellers.add(traveller2);

        booking = routeFlightData.createBooking(flightBookingInputDto);
        assertNotNull(booking);
        assertEquals(booking.getSeats().get(0), "1C");
    }

}
