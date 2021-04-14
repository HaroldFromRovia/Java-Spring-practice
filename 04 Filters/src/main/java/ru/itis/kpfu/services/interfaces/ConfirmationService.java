package ru.itis.kpfu.services.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface ConfirmationService {

    boolean confirm(String token);

    boolean isConfirmed(String email);

}
