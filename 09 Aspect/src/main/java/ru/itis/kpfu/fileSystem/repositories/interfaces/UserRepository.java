package ru.itis.kpfu.fileSystem.repositories.interfaces;

import ru.itis.kpfu.fileSystem.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByName(String name);

    Optional<User> findUserByEmail(String email);

}
