package ru.itis.kpfu.bentos.springboothomework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.kpfu.bentos.springboothomework.dto.UserDto;
import ru.itis.kpfu.bentos.springboothomework.services.interfaces.UserService;
import ru.itis.kpfu.bentos.springboothomework.utils.DateDirective;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getUsers(ModelMap map) {
        map.put("users", userService.getUsers()
                .stream()
                .map(UserDto::from)
                .collect(Collectors.toList()));
        map.put("date", LocalDateTime.now().minusMinutes(4));
        map.put("timeView", new DateDirective());
        return "users";
    }

}
