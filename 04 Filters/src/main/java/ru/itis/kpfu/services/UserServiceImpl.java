package ru.itis.kpfu.services;

import ru.itis.kpfu.dto.UserDto;
import ru.itis.kpfu.models.User;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.repositories.interfaces.UserRepository;
import ru.itis.kpfu.services.interfaces.ConverterService;
import ru.itis.kpfu.services.interfaces.PasswordService;
import ru.itis.kpfu.services.interfaces.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final ConverterService converterService;

    public UserServiceImpl(UserRepository userRepository, PasswordService passwordService, ConverterService converterService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.converterService = converterService;
    }

    public Optional<User> getUser(String email, String password) {
        var user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            if (passwordService.compare(password, user.get().getPasswordHash())) {
                return user;
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUser(String token) {
        return userRepository.findUserByRegToken(token);
    }

    public List<UserDto> getUsersDto() {
        return userRepository.findAll()
                .stream()
                .map(converterService::toData)
                .collect(Collectors.toList());
    }
}
