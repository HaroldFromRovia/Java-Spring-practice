package ru.itis.kpfu.cloudlabwebapp.service;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.ListBlobsOptions;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@MultipartConfig
public class FileServiceImpl implements FileService {

    private final BlobServiceClient blobServiceClient;
    private final String CONTAINER_NAME = "cloudlab-app-storage";
    private final String STORAGE_PATH = System.getProperty("user.home") + "\\Downloads\\";
    private final ListBlobsOptions options;
    private final BlobContainerClient container;

    public FileServiceImpl(BlobServiceClient blobServiceClient, ListBlobsOptions options) {
        this.blobServiceClient = blobServiceClient;
        this.options = options;
        this.container = blobServiceClient.getBlobContainerClient(CONTAINER_NAME);
    }

    @SneakyThrows
    @Override
    public String saveFile(MultipartFile file) {

        var fileName = UUID.randomUUID().toString();
        var path = STORAGE_PATH + fileName;
        var targetFile = new File(path);
        Map<String, String> originMap = new HashMap<>();

        originMap.put("originalName", Objects.requireNonNull(file.getOriginalFilename()).replaceAll(" ", ""));

        FileUtils.copyInputStreamToFile(file.getInputStream(), targetFile);

        var blobClient = container.getBlobClient(fileName);

        blobClient.uploadFromFile(path);
        blobClient.setMetadata(originMap);

        targetFile.delete();
        return fileName;
    }

    @Override
    public File getFile(String fileName) throws IllegalArgumentException {

        var originalFileName = getOriginName(fileName);

        var path = STORAGE_PATH + originalFileName;
        File file = new File(path);
        if (file.exists()) {
            file.renameTo(new File(path.replace(path, STORAGE_PATH
                    + "(" + UUID.randomUUID().toString().substring(35)) + ")" + originalFileName));
        }
        container.getBlobClient(fileName).downloadToFile(path);
        container.getBlobClient(fileName).delete();
        return file;
    }

    @Override
    public String getOriginName(String fileName) {
        var responseBlob = container.listBlobs(options, null)
                .stream()
                .filter((blob) -> blob.getName().equals(fileName))
                .collect(Collectors.toList());

        if (responseBlob.size() == 1) {

            Map<String, String> metadata = responseBlob.get(0).getMetadata();

            return metadata.get("originalName");
        } else throw new IllegalArgumentException("Wrong file name, or file were removed");
    }
}
