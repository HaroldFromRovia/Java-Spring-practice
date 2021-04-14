package ru.itis.kpfu.bentos.springboothomework.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.springboothomework.dto.UserDto;
import ru.itis.kpfu.bentos.springboothomework.repository.interfaces.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginLogoutHandler extends SimpleUrlAuthenticationSuccessHandler implements LogoutHandler {
    private final UserRepository userRepository;

    public LoginLogoutHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
        super.setUseReferer(true);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var session = request.getSession();
        var details = (UserDetails) authentication.getPrincipal();

        var user = userRepository.findByName(details.getUsername()).orElseThrow(
                () -> new IllegalStateException("user is authorized but not in database: " + details.getUsername())
        );

        System.out.println("setting session for " + user.getName());
        session.setAttribute("user", user);
        session.setAttribute("me", UserDto.from(user));

        super.onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        var session = request.getSession();
        System.out.println("cleaning session for " + session.getAttribute("me"));

        session.setAttribute("user", null);
        session.setAttribute("me", null);
    }
}
