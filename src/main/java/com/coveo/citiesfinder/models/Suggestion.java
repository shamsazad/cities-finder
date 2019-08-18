package com.coveo.citiesfinder.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
public class Suggestion implements Comparable<Suggestion>, Serializable {

    @NotNull
    private String name;
    private Double latitude;
    private Double longitude;
    private float score;
    @JsonIgnore
    private double distance;
    @JsonIgnore
    private long population;

    @Override
    public int compareTo(Suggestion s) {

        if(this.score > s.score)
            return -1;
        if(this.score < s.score)
            return 1;
        return 0;
    }
}
