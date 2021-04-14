package ru.itis.kpfu.fileSystem.controllers;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.kpfu.fileSystem.services.interfaces.FileService;
import ru.itis.kpfu.fileSystem.services.interfaces.SmtpService;
import ru.itis.kpfu.fileSystem.services.interfaces.TemplateService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/files")
@MultipartConfig
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/upload")
    public String getUploadTemplate() {
        return "uploadTheFile";
    }

    @GetMapping
    public String getPage() {
        return getUploadTemplate();
    }

    @SneakyThrows
    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile, ModelMap modelMap) {

        fileService.saveFile(multipartFile);

        modelMap.put("action", "upload");
        return "actionSuccess";
    }

    @GetMapping(value = "/{file-name:.+}")
    public String getFile(@PathVariable("file-name") String fileName, HttpServletResponse response, ModelMap map) {
        try {
            fileService.getFile(fileName, response);
            map.put("action", "download");
            return "actionSuccess";
        } catch (IllegalArgumentException | IOException e) {
            map.put("action", "download");
            return "actionSuccess";
        }
    }
}
