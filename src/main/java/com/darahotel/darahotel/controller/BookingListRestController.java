package com.darahotel.darahotel.controller;

import com.darahotel.darahotel.dto.request.BookingListRequestDTO;
import com.darahotel.darahotel.dto.response.ApiBaseResponse;
import com.darahotel.darahotel.dto.response.BookingResponse;
import com.darahotel.darahotel.service.BookingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookingListRestController {

    private final BookingListService bookingListService;

    @PostMapping("/create_booking_list")
    public ResponseEntity<ApiBaseResponse<BookingResponse>> createBookingList(@RequestBody BookingListRequestDTO bookingList) {
        ApiBaseResponse<BookingResponse> response = new ApiBaseResponse<>();

        BookingResponse bookingResponse = bookingListService.bookingList(bookingList);

        response.setData(bookingResponse);
        response.setStatus(HttpStatus.OK);
        response.setMessage("success");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get_all/{id}")
    public ResponseEntity<ApiBaseResponse<BookingResponse>> getAllBookingsListById(@PathVariable("id") Long id) {
        ApiBaseResponse<BookingResponse> response = new ApiBaseResponse<>();

        BookingResponse bookingResponse = bookingListService.getBookingById(id);
        response.setData(bookingResponse);
        response.setStatus(HttpStatus.OK);
        response.setMessage("success");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update_booking_list/{id}")
    public ResponseEntity<ApiBaseResponse<BookingResponse>> updateBookingList(@PathVariable("id") Long id,
                                                                              @RequestBody BookingListRequestDTO bookingList) {
        ApiBaseResponse<BookingResponse> response = new ApiBaseResponse<>();

        BookingResponse bookingResponse = bookingListService.updateBooking(id, bookingList);
        response.setData(bookingResponse);
        response.setStatus(HttpStatus.OK);
        response.setMessage("success");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/cancel_booking_list/{id}")
    public ResponseEntity<ApiBaseResponse<BookingResponse>> deleteBookingList(@PathVariable("id") Long id ,
                                                                              @RequestBody BookingListRequestDTO bookingList) {
        ApiBaseResponse<BookingResponse> response = new ApiBaseResponse<>();
        response.setData(bookingListService.deleteBooking(id, bookingList));
        response.setStatus(HttpStatus.OK);
        response.setMessage("success");
        return ResponseEntity.ok(response);
    }

}
