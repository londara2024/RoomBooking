package com.darahotel.darahotel.controller;


import com.darahotel.darahotel.dto.request.RoomDTO;
import com.darahotel.darahotel.dto.response.ApiBaseResponse;
import com.darahotel.darahotel.entity.Room;
import com.darahotel.darahotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RoomRestController {

    private final RoomService roomService;

    @PostMapping("/createdRoom")
    public ResponseEntity<ApiBaseResponse<Room>> submitRoom(@RequestBody RoomDTO baseCreatedRoom) {
        ApiBaseResponse<Room> response = new ApiBaseResponse();
        Room room = roomService.postRoom(baseCreatedRoom);

        response.setData(room);
        response.setStatus(HttpStatus.OK);
        response.setMessage("success");

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiBaseResponse<List<Room>>> getAllRoom() {
        ApiBaseResponse<List<Room>> response = new ApiBaseResponse();
        List<Room> rooms = roomService.getRoomList();

        response.setData(rooms);
        response.setStatus(HttpStatus.OK);
        response.setMessage("success");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/room/{id}")
    public ResponseEntity<ApiBaseResponse<Room>> getByIdRoom(@PathVariable Long id) {
        ApiBaseResponse<Room> response = new ApiBaseResponse();
        Room room = roomService.getRoomById(id);
        response.setData(room);
        response.setStatus(HttpStatus.OK);
        response.setMessage("success");
        return ResponseEntity.ok(response);
    }

}
