package com.devon.interview.services.impl;

import com.devon.interview.dtos.FlightBookingInputDto;
import com.devon.interview.dtos.FlightBookingOutputDto;
import com.devon.interview.dtos.FlightSeatDetails;
import com.devon.interview.services.RouteFlightData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class RouteFlightDataImpl implements RouteFlightData {

    private Map<String, FlightSeatDetails> routes = new HashMap<>();

    @Override
    public void creteFlightDetails() {

        for (int i = 0; i < 10; i++) {
            String route = "route" + i;
            routes.put(route, new FlightSeatDetails(route, 30, true));
        }
        for (int i = 10; i < 15; i++) {
            String route = "route" + i;
            routes.put("route" + i, new FlightSeatDetails(route, 30, true));
        }

    }


    @Override
    public Set<String> getRoutes() {
        return routes.keySet();
    }

    @Override
    public FlightBookingOutputDto createBooking(FlightBookingInputDto flightBookingDto) {
        creteFlightDetails();
        FlightSeatDetails flightSeatDetails = routes.get(flightBookingDto.getRoute());
        if(Objects.nonNull(flightSeatDetails)) {
            int travellers = flightBookingDto.getTravellers().size();
            FlightBookingOutputDto flightBookingOutputDto = new FlightBookingOutputDto();
            BeanUtils.copyProperties(flightBookingDto, flightBookingOutputDto);
            createBookings(flightSeatDetails, travellers, flightBookingOutputDto);
            flightBookingOutputDto.setPnr(UUID.randomUUID().toString());
            return flightBookingOutputDto;
        } else {
            return null;
        }
    }

    private void createBookings(FlightSeatDetails flightSeatDetails, int travellers, FlightBookingOutputDto flightBookingOutputDto) {
        if (!flightSeatDetails.isDomestic() && travellers == 4) {
            bookingForPax4(flightSeatDetails, travellers, flightBookingOutputDto);
        } else if (travellers == 3) {
            bookingForPax3(flightSeatDetails, travellers, flightBookingOutputDto);
        } else if (travellers == 2) {
            bookingForPax2(flightSeatDetails, travellers, flightBookingOutputDto);
        } else {
            bookingForRestAll(flightSeatDetails, travellers, flightBookingOutputDto);
        }
    }

    private void bookingForRestAll(FlightSeatDetails flightSeatDetails, int travellers, FlightBookingOutputDto flightBookingOutputDto) {
        List<String> allocatedSeats = new ArrayList<>();
        for (Map.Entry<Integer, Map<Character, Boolean>> e : flightSeatDetails.getTotalSeats().entrySet()) {
            Integer row = e.getKey();
            Map<Character, Boolean> seats = e.getValue();
            for (Map.Entry<Character, Boolean> entry : seats.entrySet()) {
                Character seat = entry.getKey();
                Boolean availability = entry.getValue();
                if (availability && travellers > 0) {
                    seats.put(seat, false);
                    --travellers;
                    allocatedSeats.add(row + "" + seat);
                }
                if(travellers == 0) {
                    break;
                }
            }
            if(travellers == 0) {
                break;
            }
        }
        flightBookingOutputDto.setSeats(allocatedSeats);
    }

    private void bookingForPax4(FlightSeatDetails flightSeatDetails, int travellers, FlightBookingOutputDto flightBookingOutputDto) {
        List<String> allocatedSeats = new ArrayList<>();
        for (Map.Entry<Integer, Map<Character, Boolean>> e : flightSeatDetails.getTotalSeats().entrySet()) {
            Integer row = e.getKey();
            Map<Character, Boolean> seats = e.getValue();
            boolean bookSeats = false;
            for (Map.Entry<Character, Boolean> entry : seats.entrySet()) {
                Character seat = entry.getKey();
                Boolean availability = entry.getValue();
                if (seat == 'D' && availability) {
                    bookSeats = true;
                }
                if (bookSeats && travellers > 0) {
                    seats.put(seat, false);
                    --travellers;
                    allocatedSeats.add(row + "" + seat);
                }
                if(travellers == 0) {
                    break;
                }
            }
            if(travellers == 0) {
                break;
            }
        }
        flightBookingOutputDto.setSeats(allocatedSeats);
    }

    private void bookingForPax3(FlightSeatDetails flightSeatDetails, int travellers, FlightBookingOutputDto flightBookingOutputDto) {
        List<String> allocatedSeats = new ArrayList<>();
        for (Map.Entry<Integer, Map<Character, Boolean>> e : flightSeatDetails.getTotalSeats().entrySet()) {
            Integer row = e.getKey();
            Map<Character, Boolean> seats = e.getValue();
            boolean bookSeats = false;
            for (Map.Entry<Character, Boolean> entry : seats.entrySet()) {
                Character seat = entry.getKey();
                Boolean availability = entry.getValue();
                if (seat == 'C' && availability) {
                    bookSeats = true;
                }
                if (bookSeats && travellers > 0) {
                    seats.put(seat, false);
                    allocatedSeats.add(row + "" + seat);
                    --travellers;
                }
                if(travellers == 0) {
                    break;
                }
            }
            if(travellers == 0) {
                break;
            }
        }
        flightBookingOutputDto.setSeats(allocatedSeats);
    }

    private void bookingForPax2(FlightSeatDetails flightSeatDetails, int travellers, FlightBookingOutputDto flightBookingOutputDto) {
        List<String> allocatedSeats = new ArrayList<>();
        for (Map.Entry<Integer, Map<Character, Boolean>> e : flightSeatDetails.getTotalSeats().entrySet()) {
            Integer row = e.getKey();
            Map<Character, Boolean> seats = e.getValue();
            boolean bookSeats = false;
            for (Map.Entry<Character, Boolean> entry : seats.entrySet()) {
                Character seat = entry.getKey();
                Boolean availability = entry.getValue();
                if (seat == 'A' && availability) {
                    bookSeats = true;
                } else if (seat == 'F' && availability) {
                    bookSeats = true;
                }
                if (bookSeats && travellers > 0) {
                    seats.put(seat, false);
                    allocatedSeats.add(row + "" + seat);
                    --travellers;
                }
                if(travellers == 0) {
                    break;
                }
            }
            if(travellers == 0) {
                break;
            }
        }
        flightBookingOutputDto.setSeats(allocatedSeats);
    }


}
