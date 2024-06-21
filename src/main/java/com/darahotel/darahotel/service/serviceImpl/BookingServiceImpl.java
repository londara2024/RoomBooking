package com.darahotel.darahotel.service.serviceImpl;

import com.darahotel.darahotel.dto.request.BookingDTO;
import com.darahotel.darahotel.entity.Booking;
import com.darahotel.darahotel.exception.ResultNotFoundException;
import com.darahotel.darahotel.repository.BookingRepository;
import com.darahotel.darahotel.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking booking(BookingDTO bookingDTO) {
        Booking booking = new Booking(bookingDTO);
        booking.setBookingPrice(BigDecimal.valueOf(0));
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> bookList() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResultNotFoundException("Booking", String.valueOf(id)));
    }

    @Override
    public Booking updateBooking(Long id, BookingDTO bookingDTO) {
        Booking booking = getBookingById(id);

        if (bookingDTO.getCustomerName() != null) {
            booking.setCustomerName(bookingDTO.getCustomerName());
        }
        if (bookingDTO.getCustomerAddress() != null) {
            booking.setCustomerAddress(bookingDTO.getCustomerAddress());
        }
        if (bookingDTO.getCustomerEmail() != null) {
            booking.setCustomerEmail(bookingDTO.getCustomerEmail());
        }
        if (bookingDTO.getCustomerPhone() != null) {
            booking.setCustomerPhone(bookingDTO.getCustomerPhone());
        }
        if (bookingDTO.getBookingPrice() != null) {
            booking.setBookingPrice(bookingDTO.getBookingPrice());
        }
        if (bookingDTO.getStartDate() != null) {
            booking.setStartDate(bookingDTO.getStartDate());
        }
        if (bookingDTO.getEndDate() != null) {
            booking.setEndDate(bookingDTO.getEndDate());
        }
        if (!String.valueOf(bookingDTO.getStatus()).equals("")) {
            booking.setStatus(bookingDTO.getStatus());
        }

        return bookingRepository.save(booking);
    }
}
