package ru.itis.kpfu.services.interfaces;

import ru.itis.kpfu.models.User;

public interface SignUpService {
    public User signUp(String email, String name, String password);
}
