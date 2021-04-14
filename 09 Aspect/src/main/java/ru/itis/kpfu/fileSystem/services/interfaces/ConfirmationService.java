package ru.itis.kpfu.fileSystem.services.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface ConfirmationService {

    boolean confirm(String token);

    boolean isConfirmed(String email);

}
