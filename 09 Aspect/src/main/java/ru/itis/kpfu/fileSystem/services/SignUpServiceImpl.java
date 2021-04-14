package ru.itis.kpfu.fileSystem.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.fileSystem.models.User;
import ru.itis.kpfu.fileSystem.repositories.interfaces.UserRepository;
import ru.itis.kpfu.fileSystem.services.interfaces.PasswordService;
import ru.itis.kpfu.fileSystem.services.interfaces.SignUpService;

import java.util.regex.Pattern;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public SignUpServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signUp(String email, String name, String password) {

        User user = User.builder()
                .email(email)
                .name(name)
                .role(User.Role.USER)
                .state(User.State.NON_CONFIRMED)
                .passwordHash(passwordEncoder.encode(password))
                .build();

        userRepository.save(user);
        return user;
    }


    public boolean isEmailValid(String email) {
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    public boolean isEmailTaken(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

    public boolean isNameTaken(String name) {
        return userRepository.findUserByName(name).isPresent();
    }


}
