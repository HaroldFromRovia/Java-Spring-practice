package ru.itis.kpfu.services;

import org.springframework.stereotype.Service;
import ru.itis.kpfu.services.interfaces.CookieService;

import javax.servlet.http.Cookie;
import java.util.Optional;

@Service
public class CookieServiceImpl implements CookieService {

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

    public Cookie configure(Cookie cookie){
        cookie.setPath("/");
        cookie.setMaxAge(630720000);
        return cookie;
    }
}
