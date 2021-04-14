package ru.itis.kpfu.services.interfaces;

public interface PasswordService {

    String createPasswordHash(String password);

    boolean compare(String password, String passwordHash);
}
