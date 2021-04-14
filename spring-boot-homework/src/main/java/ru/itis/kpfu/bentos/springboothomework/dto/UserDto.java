package ru.itis.kpfu.bentos.springboothomework.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.kpfu.bentos.springboothomework.models.User;

@Data
@Builder
public class UserDto {
    private Long id;
    private String username;

    public static UserDto from(User user) {
        return builder()
                .id(user.getId())
                .username(user.getName())
                .build();
    }
}
