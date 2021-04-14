package ru.itis.kpfu.fileSystem.services;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.kpfu.fileSystem.models.FileInfo;
import ru.itis.kpfu.fileSystem.repositories.interfaces.FileInfoRepository;
import ru.itis.kpfu.fileSystem.services.interfaces.ConverterService;
import ru.itis.kpfu.fileSystem.services.interfaces.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {

    private final FileInfoRepository fileInfoRepository;
    private final ConverterService converterService;
    private final Environment environment;

    public FileServiceImpl(FileInfoRepository fileInfoRepository, ConverterService converterService, Environment environment) {
        this.fileInfoRepository = fileInfoRepository;
        this.converterService = converterService;
        this.environment = environment;
    }

    @SneakyThrows
    @Override
    public String saveFile(MultipartFile file) {
        var fileInfo = converterService.toFileInfo(file);
        File targetFile = new File(Objects.requireNonNull(environment.getProperty("storage.path")) + fileInfo.getStorageFileName());
        FileUtils.copyInputStreamToFile(file.getInputStream(), targetFile);
        fileInfoRepository.save(fileInfo);
        return fileInfo.getStorageFileName();
    }

    @Override
    public void getFile(String fileName, HttpServletResponse response) throws IllegalArgumentException, IOException {

        var fileInfoCandidate = fileInfoRepository.findFileInfoByName(fileName);

        if (fileInfoCandidate.isPresent()) {

            var fileInfo = fileInfoCandidate.get();
            response.setContentType(fileInfo.getType());
            InputStream inputStream = new FileInputStream(new File(fileInfo.getUrl()));


            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();

        } else
            throw new IllegalArgumentException("A file with this name wasn't found ");

    }

    @Override
    public FileInfo getFileInfo(File file) {
        //TODO Можно перенести в утилиты
        return null;
    }

}
