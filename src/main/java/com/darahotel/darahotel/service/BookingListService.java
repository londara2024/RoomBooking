package com.darahotel.darahotel.service;

import com.darahotel.darahotel.dto.request.BookingListRequestDTO;
import com.darahotel.darahotel.dto.response.BookingResponse;


public interface BookingListService {
    BookingResponse bookingList(BookingListRequestDTO booking);
    BookingResponse getBookingById(Long bookingId);
    BookingResponse updateBooking(Long bookingId, BookingListRequestDTO booking);

    BookingResponse deleteBooking(Long bookingId, BookingListRequestDTO booking);
}
