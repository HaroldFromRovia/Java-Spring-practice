package ru.itis.kpfu.cloudlabwebapp.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public interface FileService {

    String saveFile(MultipartFile file);

    File getFile(String fileName) throws IllegalArgumentException, IOException;

    String getOriginName(String fileName);

}
