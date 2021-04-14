package ru.itis.kpfu.fileSystem.services.interfaces;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.kpfu.fileSystem.dto.FileInfoDto;
import ru.itis.kpfu.fileSystem.dto.UserDto;
import ru.itis.kpfu.fileSystem.models.FileInfo;
import ru.itis.kpfu.fileSystem.models.User;

@Service
public interface ConverterService {

    UserDto toData(User user);

    FileInfoDto toData(FileInfo fileInfo);

    FileInfo toFileInfo(MultipartFile file);


}
