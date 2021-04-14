package ru.itis.kpfu.fileSystem.services.interfaces;

import org.springframework.stereotype.Service;
import ru.itis.kpfu.fileSystem.dto.UserDto;
import ru.itis.kpfu.fileSystem.models.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Optional<User> getUser(String email, String password);

    List<UserDto> getUsersDto();
}
