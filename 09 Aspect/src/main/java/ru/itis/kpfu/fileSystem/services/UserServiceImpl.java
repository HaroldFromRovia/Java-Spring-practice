package ru.itis.kpfu.fileSystem.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.fileSystem.dto.UserDto;
import ru.itis.kpfu.fileSystem.models.User;
import ru.itis.kpfu.fileSystem.repositories.interfaces.UserRepository;
import ru.itis.kpfu.fileSystem.services.interfaces.ConverterService;
import ru.itis.kpfu.fileSystem.services.interfaces.PasswordService;
import ru.itis.kpfu.fileSystem.services.interfaces.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConverterService converterService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ConverterService converterService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.converterService = converterService;
    }

    public Optional<User> getUser(String email, String password) {
        var user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            if (passwordEncoder.matches(password, user.get().getPasswordHash())) {
                return user;
            }
        }
        return Optional.empty();
    }

    public List<UserDto> getUsersDto() {
        return userRepository.findAll()
                .stream()
                .map(converterService::toData)
                .collect(Collectors.toList());
    }
}
