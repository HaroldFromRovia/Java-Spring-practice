package ru.itis.kpfu.fileSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class FileInfoDto {

    private String originalFileName;
    private Long size;
    private String type;

}
