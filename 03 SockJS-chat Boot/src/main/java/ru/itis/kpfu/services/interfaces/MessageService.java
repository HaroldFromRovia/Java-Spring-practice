package ru.itis.kpfu.services.interfaces;

import ru.itis.kpfu.dto.MessageDto;
import ru.itis.kpfu.models.Room;

import java.util.List;

public interface MessageService {

    List<MessageDto> getAllByRoom(Room room);
    List<MessageDto> getAllByRoomId(Long roomId);

}
