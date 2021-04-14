package ru.itis.kpfu.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.dto.MessageDto;
import ru.itis.kpfu.models.Room;
import ru.itis.kpfu.repositories.MessageRepository;
import ru.itis.kpfu.services.interfaces.MessageService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public List<MessageDto> getAllByRoom(Room room) {
        return null;
    }

    @Override
    public List<MessageDto> getAllByRoomId(Long roomId) {
        return messageRepository.findAllByRoomId(roomId)
                .stream()
                .map(MessageDto::from)
                .collect(Collectors.toList());
    }

}
