package ru.itis.kpfu.fileSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.kpfu.fileSystem.services.interfaces.SignUpService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping
    public String getPage() {
        return "sign_up";
    }

    @PostMapping
    public String signUp(HttpServletRequest req) {
        signUpService.signUp(req.getParameter("email"), req.getParameter("name"), req.getParameter("password"));
        return "redirect:/signIn";
    }
}
