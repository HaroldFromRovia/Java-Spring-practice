package ru.itis.kpfu.fileSystem.repositories.interfaces;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    void save(T model);

    Optional<T> findById(ID id);

    void delete(ID id);

    List<T> findAll();
}
