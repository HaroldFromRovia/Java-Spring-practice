package ru.itis.kpfu.fileSystem.repositories.interfaces;

import ru.itis.kpfu.fileSystem.models.FileInfo;

import java.util.Optional;

public interface FileInfoRepository extends CrudRepository<FileInfo, Long> {
    Optional<FileInfo> findFileInfoByName(String name);
}
