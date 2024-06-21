package com.darahotel.darahotel.controller;

import com.darahotel.darahotel.dto.request.RoomTypeDTO;
import com.darahotel.darahotel.dto.response.ApiBaseResponse;
import com.darahotel.darahotel.entity.RoomType;
import com.darahotel.darahotel.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RoomTypeRestController {

    private final RoomTypeService roomTypeService;

    @PostMapping("/createdRoomType")
    public ResponseEntity<ApiBaseResponse<RoomType>> createRoomType(@RequestBody RoomTypeDTO roomTypeDTO) {
        ApiBaseResponse<RoomType> response = new ApiBaseResponse<>();

        RoomType roomType = roomTypeService.createdRoomType(roomTypeDTO);
        response.setData(roomType);
        response.setStatus(HttpStatus.CREATED);
        response.setMessage("success");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<ApiBaseResponse<List<RoomType>>> getAllRoomType() {
        ApiBaseResponse<List<RoomType>> response = new ApiBaseResponse<>();

        List<RoomType> roomTypes = roomTypeService.getAllRoomTypes();
        response.setData(roomTypes);
        response.setStatus(HttpStatus.OK);
        response.setMessage("success");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiBaseResponse<RoomType>> updateRoomType(@PathVariable Long id,
                                                                    @RequestBody RoomTypeDTO roomTypeDTO) {
        ApiBaseResponse<RoomType> response = new ApiBaseResponse<>();

        RoomType roomType = roomTypeService.updateRoomTypes(id, roomTypeDTO);
        response.setData(roomType);
        response.setStatus(HttpStatus.OK);
        response.setMessage("success");

        return ResponseEntity.ok(response);
    }
}
