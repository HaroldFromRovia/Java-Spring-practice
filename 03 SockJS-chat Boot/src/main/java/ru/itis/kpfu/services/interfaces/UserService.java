package ru.itis.kpfu.services.interfaces;

import ru.itis.kpfu.dto.UserDto;
import ru.itis.kpfu.dto.forms.SignInForm;
import ru.itis.kpfu.dto.forms.SignUpForm;
import ru.itis.kpfu.models.User;

public interface UserService {


    UserDto signIn(SignInForm signInForm);
    UserDto signUp(SignUpForm signUpForm);

}
