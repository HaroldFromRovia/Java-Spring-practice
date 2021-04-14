package ru.itis.kpfu.bentos.springboothomework.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.kpfu.bentos.springboothomework.forms.SignUpForm;
import ru.itis.kpfu.bentos.springboothomework.models.User;
import ru.itis.kpfu.bentos.springboothomework.repository.interfaces.UserRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class UsersRestController {

    private final UserRepository userRepository;

    public UsersRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Wrong id"));
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @DeleteMapping
    public void removeUser(long id) {
        userRepository.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<SignUpForm> saveUser(@Valid SignUpForm user) {
        userRepository.save(User.builder()
                .email(user.getEmail())
                .name(user.getUsername())
                .birthday(user.getBirthDate())
                .country(user.getCountry())
                .gender(user.getGender())
                .passwordHash(user.getPassword())
                .build());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
