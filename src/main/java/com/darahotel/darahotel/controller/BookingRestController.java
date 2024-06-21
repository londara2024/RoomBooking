package com.darahotel.darahotel.controller;

import com.darahotel.darahotel.dto.request.BookingDTO;
import com.darahotel.darahotel.dto.response.ApiBaseResponse;
import com.darahotel.darahotel.entity.Booking;
import com.darahotel.darahotel.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookingRestController {

    private final BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<ApiBaseResponse<Booking>> bookingRoom (@RequestBody BookingDTO bookingDTO) {
        ApiBaseResponse<Booking> response = new ApiBaseResponse<>();
        Booking booking = bookingService.booking(bookingDTO);
        response.setData(booking);
        response.setStatus(HttpStatus.OK);
        response.setMessage("success");

        return ResponseEntity.ok(response);
    }


    @GetMapping("/get_all_booking")
    public ResponseEntity<ApiBaseResponse<List<Booking>>> listAllBooking () {
        ApiBaseResponse<List<Booking>> response = new ApiBaseResponse<>();
        List<Booking> bookings = bookingService.bookList();

        response.setData(bookings);
        response.setStatus(HttpStatus.OK);
        response.setMessage("success");

        return ResponseEntity.ok(response);
    }


    @GetMapping("/get_booking/{id}")
    public ResponseEntity<ApiBaseResponse<Booking>> getBookingById (@PathVariable("id") Long id) {
        ApiBaseResponse<Booking> response = new ApiBaseResponse<>();
        Booking booking = bookingService.getBookingById(id);
        response.setData(booking);
        response.setStatus(HttpStatus.OK);
        response.setMessage("success");

        return ResponseEntity.ok(response);
    }

}
