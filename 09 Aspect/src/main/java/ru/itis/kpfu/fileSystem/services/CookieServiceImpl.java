package ru.itis.kpfu.fileSystem.services;

import org.springframework.stereotype.Service;
import ru.itis.kpfu.fileSystem.services.interfaces.ConverterService;
import ru.itis.kpfu.fileSystem.services.interfaces.CookieService;

import javax.servlet.http.Cookie;
import java.util.Optional;

@Service
public class CookieServiceImpl implements CookieService {
    @Override
    public Optional<Cookie> findCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return Optional.of(cookie);
                }
            }
        }
        return Optional.empty();
    }
}
