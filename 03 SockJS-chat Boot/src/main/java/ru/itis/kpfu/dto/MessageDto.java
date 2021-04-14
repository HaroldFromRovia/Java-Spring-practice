package ru.itis.kpfu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.kpfu.models.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {

    private String text;
    private UserDto from;
    private RoomDto room;

    public static MessageDto from(Message message) {
        return MessageDto.builder()
                .text(message.getText())
                .from(UserDto.from(message.getFrom()))
                .build();
    }
}
