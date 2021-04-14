package ru.itis.kpfu.bentos.springboothomework.repository.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.bentos.springboothomework.aop.annotations.LogMethod;
import ru.itis.kpfu.bentos.springboothomework.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @LogMethod
    Optional<User> findByName(String name);
}
