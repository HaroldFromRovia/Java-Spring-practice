package ru.itis.kpfu.bentos.sockjsboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private String text;
    private String from;
}
