package ru.itis.kpfu.services;

import ru.itis.kpfu.dto.UserDto;
import ru.itis.kpfu.models.User;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.services.interfaces.ConverterService;

@Service
public class ConverterServiceImpl implements ConverterService {
    @Override
    public UserDto toData(User user) {
        return UserDto.builder()
                .name(user.getName())
                .build();
    }
}
