package ru.itis.kpfu.bentos.springboothomework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.kpfu.bentos.springboothomework.dto.UserDto;
import ru.itis.kpfu.bentos.springboothomework.forms.SignUpForm;
import ru.itis.kpfu.bentos.springboothomework.models.User;
import ru.itis.kpfu.bentos.springboothomework.repository.myBatis.UserMapper;
import ru.itis.kpfu.bentos.springboothomework.utils.DateDirective;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/myBatis")
public class MyBatisController {

    private final UserMapper userMapper;


    public MyBatisController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/all")
    public String getUsers(ModelMap map) {
        map.put("users", userMapper.findAll()
                .stream()
                .map(UserDto::from)
                .collect(Collectors.toList()));
        map.put("date", LocalDateTime.now().minusMinutes(50));
        map.put("timeView", new DateDirective());
        return "users";
    }

    @GetMapping
    public String getIndex(@ModelAttribute("user") SignUpForm form) {
        return "myBatisSignUp";
    }

    @PostMapping
    public String formPost(@Valid @ModelAttribute("user") SignUpForm form, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userMapper.save(User.builder()
                    .email(form.getEmail())
                    .name(form.getUsername())
                    .passwordHash(form.getPassword())
                    .birthday(form.getBirthDate())
                    .country(form.getCountry())
                    .gender(form.getGender())
                    .build());
            return "myBatisSignUp";
        }
        return "myBatisSignUp";
    }

}
