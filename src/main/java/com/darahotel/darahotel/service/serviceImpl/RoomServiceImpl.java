package com.darahotel.darahotel.service.serviceImpl;

import com.darahotel.darahotel.dto.request.RoomDTO;
import com.darahotel.darahotel.entity.Room;
import com.darahotel.darahotel.entity.RoomType;
import com.darahotel.darahotel.exception.ResultNotFoundException;
import com.darahotel.darahotel.repository.RoomRepository;
import com.darahotel.darahotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomTypeServiceImpl roomTypeService;

    @Override
    public Room postRoom(RoomDTO createdRoom) {
        Room room = new Room();
        room.setRoomName(createdRoom.getRoomName());
        room.setRoomNumber(createdRoom.getRoomNumber());
        room.setStatus(createdRoom.getStatus());
        room.setCreatedAt(createdRoom.getCreatedAt());

        RoomType roomType = roomTypeService.getById(createdRoom.getRoomTypeId());
        room.setRoomType(roomType);

        return roomRepository.save(room);
    }

    @Override
    public List<Room> getRoomList() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResultNotFoundException("Room",String.valueOf(id)));
    }

    @Override
    public Room updateRoom(Long id, RoomDTO updatedRoom) {
        Room room = getRoomById(id);

        if (updatedRoom.getRoomName() != null) {
            room.setRoomName(updatedRoom.getRoomName());
        }
        if (updatedRoom.getRoomNumber() != null) {
            room.setRoomNumber(updatedRoom.getRoomNumber());
        }
        if (updatedRoom.getCreatedAt() != null) {
            room.setCreatedAt(updatedRoom.getCreatedAt());
        }
        if (updatedRoom.getStatus() != 0) {
            room.setStatus(updatedRoom.getStatus());
        }

        return roomRepository.save(room);
    }


}
