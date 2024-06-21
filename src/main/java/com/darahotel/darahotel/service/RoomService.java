package com.darahotel.darahotel.service;

import com.darahotel.darahotel.dto.request.RoomDTO;
import com.darahotel.darahotel.entity.Room;

import java.util.List;

public interface RoomService {
    Room postRoom (RoomDTO createdRoom);
    List<Room> getRoomList ();
    Room getRoomById(Long id);
    Room updateRoom(Long id, RoomDTO updatedRoom);
}
