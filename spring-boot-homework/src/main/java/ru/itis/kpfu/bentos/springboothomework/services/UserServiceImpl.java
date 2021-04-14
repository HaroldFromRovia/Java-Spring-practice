package ru.itis.kpfu.bentos.springboothomework.services;

import org.springframework.stereotype.Service;
import ru.itis.kpfu.bentos.springboothomework.forms.SignUpForm;
import ru.itis.kpfu.bentos.springboothomework.models.User;
import ru.itis.kpfu.bentos.springboothomework.repository.interfaces.UserRepository;
import ru.itis.kpfu.bentos.springboothomework.services.interfaces.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signUp(SignUpForm form) {
        userRepository.save(User.builder()
                .email(form.getEmail())
                .name(form.getUsername())
                .passwordHash(form.getPassword())
                .birthday(form.getBirthDate())
                .country(form.getCountry())
                .gender(form.getGender())
                .build());
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }
}
