package ru.itis.kpfu.services.interfaces;

import ru.itis.kpfu.dto.UserDto;
import ru.itis.kpfu.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUser(String email, String password);

    Optional<User> getUser(String token);

    List<UserDto> getUsersDto();
}
