package com.devon.interview.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightBookingInputDto {
    @NonNull
    private Date doj;
    @NonNull
    private String destination;
    @NonNull
    private String source;
    @NonNull
    private List<Traveller> travellers;
    @NonNull
    private String route;
    private List<AddOns> addOns;

}
