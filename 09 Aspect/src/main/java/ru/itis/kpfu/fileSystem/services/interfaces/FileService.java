package ru.itis.kpfu.fileSystem.services.interfaces;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.kpfu.fileSystem.models.FileInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;

@Service
public interface FileService {

    String saveFile(MultipartFile file);

    void getFile(String fileName, HttpServletResponse response) throws IllegalArgumentException, IOException;

    FileInfo getFileInfo(File file);

}
