package ru.itis.kpfu.fileSystem.services.interfaces;

import org.springframework.stereotype.Service;
import ru.itis.kpfu.fileSystem.models.User;

@Service
public interface SignUpService {
    User signUp(String email, String name, String password);
}
