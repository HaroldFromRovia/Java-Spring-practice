package ru.itis.kpfu.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.dto.MessageDto;
import ru.itis.kpfu.models.Message;
import ru.itis.kpfu.models.Room;
import ru.itis.kpfu.models.User;
import ru.itis.kpfu.repositories.MessageRepository;
import ru.itis.kpfu.repositories.UserRepository;
import ru.itis.kpfu.services.interfaces.JsonProcessService;

@Service
@AllArgsConstructor
public class JsonProcessServiceImpl implements JsonProcessService {

    private final ObjectMapper objectMapper;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    public String parse(MessageDto messageDto) {
        return null;
    }

    @Override
    public MessageDto parse(String json) {
        MessageDto message;
        try {
            message = objectMapper.readValue(json, MessageDto.class);
            MessageDto finalMessage = message;
            messageRepository.save(Message.builder()
                    .from(userRepository
                            .getUserByName(message
                                    .getFrom()
                                    .getName())
                            .orElseThrow(() -> new IllegalArgumentException("Can't find user with name: " + finalMessage.getFrom())))
                    .room(Room.builder()
                            .id(message.getRoom().getId())
                            .build())
                    .text(message.getText())
                    .build());
            return message;
        } catch (JsonProcessingException e) {
            System.out.println("Can't process message Json " + e.getMessage());
        }
        return null;
    }
}
