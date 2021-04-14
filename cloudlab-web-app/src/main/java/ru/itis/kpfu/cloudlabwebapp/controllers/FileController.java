package ru.itis.kpfu.cloudlabwebapp.controllers;

import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.kpfu.cloudlabwebapp.service.FileService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@AllArgsConstructor
@MultipartConfig
public class FileController {

    private final FileService fileService;

    @GetMapping("/upload")
    public String getPage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile multipartFile, ModelMap modelMap) {
        var downloadLink = "download/page/" + fileService.saveFile(multipartFile);
        modelMap.addAttribute("link", downloadLink);

        return "downloadLink";
    }

    @GetMapping("/download/{file_name}")
    public void getFile(@PathVariable("file_name") String fileName, HttpServletResponse response) {
        try {
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileService.getOriginName(fileName) + "\"");
            InputStream inputStream = new FileInputStream(fileService.getFile(fileName));
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/download/page/{file_name}")
    public String getFilePage(ModelMap map, @PathVariable("file_name") String fileName) {
        map.addAttribute("fileName", fileService.getOriginName(fileName));
        map.addAttribute("link", fileName);
        return "downloadPage";
    }
}
