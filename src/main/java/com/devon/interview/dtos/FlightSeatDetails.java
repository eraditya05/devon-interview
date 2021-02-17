package com.devon.interview.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Setter
@Getter
public class FlightSeatDetails {

    private String route;
    private int rows;
    private boolean isDomestic;
    private Map<Integer, Map<Character, Boolean>> totalSeats = new LinkedHashMap<>();

    public FlightSeatDetails(String route, int rows, boolean isDomestic) {
        this.route = route;
        this.rows = rows;
        this.isDomestic = isDomestic;
        init(route, rows, isDomestic);
    }

    // Assuming all flights have the same seating arrangement
    public void init(String route, int rows, boolean isDomestic) {
        for (int i = 1; i <= rows; i++) {
            Map<Character, Boolean> allSeatsOfARow = new HashMap<>();
            allSeatsOfARow.put('A', true);
            allSeatsOfARow.put('B', true);
            allSeatsOfARow.put('C', true);
            allSeatsOfARow.put('D', true);
            allSeatsOfARow.put('E', true);
            allSeatsOfARow.put('F', true);
            allSeatsOfARow.put('G', true);
            if(!isDomestic) {
                allSeatsOfARow.put('H', true);
                allSeatsOfARow.put('I', true);
                allSeatsOfARow.put('J', true);
            }
            this.totalSeats.put(i, allSeatsOfARow);
        }
        log.info("Constructed new flight seats for route {} ", route);
    }

}
