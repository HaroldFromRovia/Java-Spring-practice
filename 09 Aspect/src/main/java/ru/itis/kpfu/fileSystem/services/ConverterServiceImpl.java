package ru.itis.kpfu.fileSystem.services;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.kpfu.fileSystem.dto.FileInfoDto;
import ru.itis.kpfu.fileSystem.dto.UserDto;
import ru.itis.kpfu.fileSystem.models.FileInfo;
import ru.itis.kpfu.fileSystem.models.User;
import ru.itis.kpfu.fileSystem.services.interfaces.ConverterService;

import java.util.Objects;
import java.util.UUID;

@Service
public class ConverterServiceImpl implements ConverterService {

    private final Environment environment;

    public ConverterServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public UserDto toData(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    @Override
    public FileInfoDto toData(FileInfo fileInfo) {
        return FileInfoDto.builder()
                .originalFileName(fileInfo.getOriginalFileName())
                .size(fileInfo.getSize())
                .type(fileInfo.getType())
                .build()
                ;
    }

    public FileInfo toFileInfo(MultipartFile file) {
        return FileInfo.builder()
                .url(environment.getProperty("storage.path") + createStorageName(Objects.requireNonNull(file.getOriginalFilename())))
                .storageFileName(createStorageName(file.getOriginalFilename()))
                .originalFileName(file.getOriginalFilename())
                .size(file.getSize())
                .type(file.getContentType())
                .build();
    }

    private String createStorageName(String originalFileName) {
        return UUID.randomUUID().toString() + "."
                + originalFileName.substring(1 + originalFileName.lastIndexOf('.'));
    }
}
