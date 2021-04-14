package ru.itis.kpfu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.kpfu.models.Message;
import ru.itis.kpfu.models.Room;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomDto {

    private List<UserDto> users;
    private List<MessageDto> messages;
    private Long id;

    public RoomDto(Integer id){
        this.id =Long.parseLong(id.toString());
    }

    public static RoomDto from(Room room) {
        return RoomDto.builder()
                .messages(RoomDto.from(room).getMessages())
                .users(RoomDto.from(room).getUsers())
                .build();
    }

}
