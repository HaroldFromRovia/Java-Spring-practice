package ru.itis.kpfu.fileSystem.services.interfaces;

public interface PasswordService {

    String createPasswordHash(String password);

    boolean compare(String password, String passwordHash);
}
