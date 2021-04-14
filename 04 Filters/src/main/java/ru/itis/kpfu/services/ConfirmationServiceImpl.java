package ru.itis.kpfu.services;

import org.springframework.stereotype.Service;
import ru.itis.kpfu.models.User;
import ru.itis.kpfu.repositories.interfaces.UserRepository;
import ru.itis.kpfu.services.interfaces.ConfirmationService;
import ru.itis.kpfu.services.interfaces.UserService;

@Service
public class ConfirmationServiceImpl implements ConfirmationService {

    private final UserRepository userRepository;
    private final UserService userService;

    public ConfirmationServiceImpl(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public boolean confirm(String token) {
        var user = userService.getUser(token);
        if (user.isPresent()) {
            userRepository.update(user.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean isConfirmed(String email) {
        if (userRepository.findUserByEmail(email).isPresent())
            return userRepository.findUserByEmail(email).get().isActivated();
        else return false;
    }
}
