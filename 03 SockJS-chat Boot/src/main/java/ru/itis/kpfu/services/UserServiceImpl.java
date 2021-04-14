package ru.itis.kpfu.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.dto.UserDto;
import ru.itis.kpfu.dto.forms.SignInForm;
import ru.itis.kpfu.dto.forms.SignUpForm;
import ru.itis.kpfu.models.User;
import ru.itis.kpfu.repositories.UserRepository;
import ru.itis.kpfu.services.interfaces.PasswordService;
import ru.itis.kpfu.services.interfaces.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordService passwordService;

    @Override
    public UserDto signIn(SignInForm signInForm) {

        var userCandidate = userRepository.getUserByName(signInForm.getName());
        if (userCandidate.isPresent()) {
            var user = userCandidate.get();
            if (passwordService.compare(signInForm.getPassword(), user.getPasswordHash())) {
                return UserDto.from(user);
            } else return null;
        } else return null;
    }

    @Override
    public UserDto signUp(SignUpForm signUpForm) {

        var passwordHash = passwordService.createPasswordHash(signUpForm.getPassword());

        var userCandidate = User.builder()
                .name(signUpForm.getName())
                .passwordHash(passwordHash)
                .build();

        var user = userRepository.save(userCandidate);

        return UserDto.from(user);
    }
}
