package ru.itis.kpfu.services.interfaces;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Optional;

@Service
public interface CookieService {
    Optional<Cookie> findCookie(Cookie[] cookies, String name);
}
