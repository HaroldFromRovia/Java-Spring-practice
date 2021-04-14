package ru.itis.kpfu.bentos.springboothomework.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.kpfu.bentos.springboothomework.models.User;

@Data
@Builder
public class LogDto {

    private String username;
    private String email;

    public static LogDto from(User user) {
        return builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();
    }
}

