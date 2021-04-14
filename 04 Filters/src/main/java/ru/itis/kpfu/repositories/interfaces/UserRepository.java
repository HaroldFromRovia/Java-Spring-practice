package ru.itis.kpfu.repositories.interfaces;

import ru.itis.kpfu.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByName(String name);

    Optional<User> findUserByRegToken(String token);

    Optional<User> findUserByEmail(String email);

    public void update(User model);
}
