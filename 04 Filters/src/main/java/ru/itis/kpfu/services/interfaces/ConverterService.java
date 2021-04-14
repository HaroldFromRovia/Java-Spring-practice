package ru.itis.kpfu.services.interfaces;

import ru.itis.kpfu.dto.UserDto;
import ru.itis.kpfu.models.User;

public interface ConverterService {

    UserDto toData(User user);

}
