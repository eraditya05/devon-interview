package com.devon.interview.dtos;

import com.devon.interview.LocalDateUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Traveller {
    @NonNull
    private Date doj;
    private String fName;
    private String mName;
    private String lName;
    private Date dob;
    private boolean infant;
    private boolean child;

    public void setInfant(boolean infant) {
        LocalDate localDateDob = LocalDateUtils.convertToLocalDateFromUtilDate(dob);
        LocalDate localDateDoj = LocalDateUtils.convertToLocalDateFromUtilDate(doj);
        Period p = Period.between(localDateDob, localDateDoj);
        if (p.getYears() <= 2) {
            this.infant = infant;
        }
    }
}
