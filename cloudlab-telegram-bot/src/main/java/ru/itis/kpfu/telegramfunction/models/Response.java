package ru.itis.kpfu.telegramfunction.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private final String method = "sendMessage";
    private String text;
    @JsonProperty("chat_id")
    private Long chat_id;

}
