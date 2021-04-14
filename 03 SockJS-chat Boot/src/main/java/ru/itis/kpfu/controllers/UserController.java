package ru.itis.kpfu.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.kpfu.dto.forms.SignInForm;
import ru.itis.kpfu.dto.forms.SignUpForm;
import ru.itis.kpfu.services.interfaces.CookieService;
import ru.itis.kpfu.services.interfaces.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final CookieService cookieService;


    @GetMapping("/signUp")
    public String getSignUp() {
        return "sign_up";
    }

    @GetMapping("/signIn")
    public String getSignIn() {
        return "sign_in";
    }

    @PostMapping("/signIn")
    public String signIn(SignInForm form, HttpServletResponse response) {
        var user = userService.signIn(form);
        var cookie = new Cookie("from", user.getName());
        if (userService.signIn(form) != null) {
            response.addCookie(cookieService.configure(cookie));
            return "redirect:/room/0";
        } else return "redirect:/signIn?error";
    }

    @PostMapping("/signUp")
    public String signUp(SignUpForm form, HttpServletResponse response) {
        var user = userService.signUp(form);
        response.addCookie(new Cookie("from", user.getName()));
        return "redirect:/room/0";
    }

}
