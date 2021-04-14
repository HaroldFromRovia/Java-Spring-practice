package ru.itis.kpfu.services.interfaces;

import ru.itis.kpfu.dto.MessageDto;

public interface JsonProcessService {

    String parse(MessageDto messageDto);
    MessageDto parse(String json);


}
