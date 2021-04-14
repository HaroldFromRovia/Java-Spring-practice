package ru.itis.kpfu.bentos.springboothomework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.kpfu.bentos.springboothomework.forms.SignUpForm;
import ru.itis.kpfu.bentos.springboothomework.services.interfaces.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/signUp")
public class SignUpController {

    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

//    @InitBinder
//    public void bindingPreparation(WebDataBinder binder) {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
//        binder.registerCustomEditor(Date.class, editor);
//    }


    @GetMapping
    public String getIndex(@ModelAttribute("user") SignUpForm form) {
        return "signUp";
    }

    @PostMapping
    public String formPost(@Valid @ModelAttribute("user") SignUpForm user, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userService.signUp(user);
            return "signUp";
        }
        return "signUp";
    }

}
