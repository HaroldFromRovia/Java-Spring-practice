package ru.itis.kpfu.services;

import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.models.User;
import ru.itis.kpfu.repositories.interfaces.UserRepository;
import ru.itis.kpfu.services.interfaces.PasswordService;
import ru.itis.kpfu.services.interfaces.SignUpService;
import ru.itis.kpfu.services.interfaces.SmtpService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final SmtpService smtpService;
    private final UserRepository userRepository;
    private final PasswordService passwordService;

    private static final String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private final String confirmLink = "localhost:8081/registrationConfirm?token=";

    public SignUpServiceImpl(SmtpService smtpService, UserRepository userRepository, PasswordService passwordService) {
        this.smtpService = smtpService;
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public User signUp(String email, String name, String password) {
        String token = UUID.randomUUID().toString();
        User user = User.builder()
                .email(email)
                .name(name)
                .passwordHash(passwordService.createPasswordHash(password))
                .isActivated(false)
                .registrationToken(token)
                .build();

        userRepository.save(user);
        try {
            //TODO Добавить Executor
            smtpService.sendMessage(email, confirmLink + token);
        } catch (IOException | TemplateException | MessagingException e) {
            throw new IllegalStateException("Don't read what i wrote here, pls");
        }

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
