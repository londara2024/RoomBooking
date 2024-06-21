package com.darahotel.darahotel.dto.request;

import com.darahotel.darahotel.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private String roomName;
    private String roomNumber;
    private int status;
    private LocalDateTime createdAt;
    private Long roomTypeId;

    public RoomDTO(Room room) {
        this.roomName = room.getRoomName();
        this.roomNumber = room.getRoomNumber();
        this.status = room.getStatus();
        this.createdAt = room.getCreatedAt();
    }
}
