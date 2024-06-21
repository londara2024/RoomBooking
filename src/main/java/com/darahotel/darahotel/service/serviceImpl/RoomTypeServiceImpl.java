package com.darahotel.darahotel.service.serviceImpl;

import com.darahotel.darahotel.dto.request.RoomTypeDTO;
import com.darahotel.darahotel.entity.Room;
import com.darahotel.darahotel.entity.RoomType;
import com.darahotel.darahotel.exception.ResultNotFoundException;
import com.darahotel.darahotel.repository.RoomTypeRepository;
import com.darahotel.darahotel.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    @Override
    public RoomType createdRoomType(RoomTypeDTO roomType) {
        RoomType savedRoomType = new RoomType();
        savedRoomType.setBed(roomType.getBed());
        savedRoomType.setBath(roomType.getBath());
        savedRoomType.setArea(roomType.getArea());
        savedRoomType.setOption(roomType.getOption());
        savedRoomType.setStatus(roomType.getStatus());
        savedRoomType.setPrice(roomType.getPrice());
        return roomTypeRepository.save(savedRoomType);
    }

    @Override
    public RoomType getById(Long id) {
        return roomTypeRepository.findById(id)
                .orElseThrow(() -> new ResultNotFoundException("RoomType",String.valueOf(id)));
    }

    @Override
    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    @Override
    public RoomType updateRoomTypes(Long id, RoomTypeDTO roomType) {
        RoomType roomDb = getById(id);
        roomDb.setPrice(roomType.getPrice());
        roomDb.setBed(roomType.getBed());
        roomDb.setBath(roomType.getBath());
        roomDb.setArea(roomType.getArea());
        roomDb.setOption(roomType.getOption());
        roomDb.setStatus(roomType.getStatus());
        return roomTypeRepository.save(roomDb);
    }
}
