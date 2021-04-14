package ru.itis.kpfu.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.services.interfaces.PasswordService;

@Service
public class PasswordServiceImpl implements PasswordService {

    public String createPasswordHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean compare(String password, String passwordHash) {
        return BCrypt.checkpw(password, passwordHash);
    }
}
