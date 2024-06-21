package com.darahotel.darahotel.service;

import com.darahotel.darahotel.dto.request.BookingDTO;
import com.darahotel.darahotel.entity.Booking;

import java.util.List;

public interface BookingService {
    Booking booking(BookingDTO bookingDTO);
    List<Booking> bookList();
    Booking getBookingById(Long id);

    Booking updateBooking(Long id, BookingDTO bookingDTO);
}
