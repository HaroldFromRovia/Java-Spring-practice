package ru.itis.kpfu.cloudlabbootfunctions.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Suggestion {

    private String value;
    @JsonProperty("unrestricted_value")
    private String unrestrictedValue;

}
