package ru.itis.kpfu.telegramfunction.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Request {

    private String query;
    @JsonProperty("branch_type")
    private final String branch_type = "MAIN";

}
