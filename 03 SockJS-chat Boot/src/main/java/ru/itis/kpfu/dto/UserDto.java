package ru.itis.kpfu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.kpfu.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String name;

    public static UserDto from(User user) {
        return UserDto.builder()
                .name(user.getName())
                .build();
    }

}
