package ru.itis.kpfu.cloudlabbootfunctions.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Location {

    @Getter
    @Setter
    private Double lat;
    @Getter
    @Setter
    private Double lon;
    @Getter
    @Setter
    private Double radius_meters;

}
