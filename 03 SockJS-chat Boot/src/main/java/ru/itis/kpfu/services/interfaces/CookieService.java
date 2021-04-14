package ru.itis.kpfu.services.interfaces;

import javax.servlet.http.Cookie;
import java.util.Optional;

public interface CookieService {

    Optional<Cookie> findCookie(Cookie[] cookies, String name);
    Cookie configure(Cookie cookie);

}
