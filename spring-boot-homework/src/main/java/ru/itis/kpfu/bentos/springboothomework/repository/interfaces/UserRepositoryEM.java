package ru.itis.kpfu.bentos.springboothomework.repository.interfaces;

import org.springframework.stereotype.Repository;
import ru.itis.kpfu.bentos.springboothomework.models.User;

import java.util.List;

@Repository
public interface UserRepositoryEM {

    void save(User user);

    User getById(Long userID);

    List<User> findAll();
}
