package com.darahotel.darahotel.service;

import com.darahotel.darahotel.dto.request.RoomTypeDTO;
import com.darahotel.darahotel.entity.RoomType;

import java.util.List;

public interface RoomTypeService {
    RoomType createdRoomType(RoomTypeDTO roomType);
    RoomType getById(Long id);
    List<RoomType> getAllRoomTypes();
    RoomType updateRoomTypes (Long id, RoomTypeDTO roomType);
}
