package ru.itis.kpfu.bentos.springboothomework.services.interfaces;

import org.springframework.stereotype.Service;
import ru.itis.kpfu.bentos.springboothomework.forms.SignUpForm;
import ru.itis.kpfu.bentos.springboothomework.models.User;

import java.util.List;

@Service
public interface UserService {
    void signUp(SignUpForm form);

    List<User> getUsers();
}
