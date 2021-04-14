package ru.itis.kpfu.fileSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.kpfu.fileSystem.repositories.interfaces.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/signIn")
public class SignInController {

    @GetMapping
    public String getSignInPage() {
        return "sign_in";
    }

}
