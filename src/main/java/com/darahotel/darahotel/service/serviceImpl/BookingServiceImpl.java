package com.darahotel.darahotel.service.serviceImpl;

import com.darahotel.darahotel.dto.request.BookingDTO;
import com.darahotel.darahotel.entity.Booking;
import com.darahotel.darahotel.exception.ResultNotFoundException;
import com.darahotel.darahotel.repository.BookingRepository;
import com.darahotel.darahotel.service.BookingService;
import com.darahotel.darahotel.utils.ValidateObj;
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

        if ( ValidateObj.validateString(bookingDTO.getCustomerName()) ) {
            booking.setCustomerName(bookingDTO.getCustomerName());
        }
        if ( ValidateObj.validateString(bookingDTO.getCustomerAddress())) {
            booking.setCustomerAddress(bookingDTO.getCustomerAddress());
        }
        if ( ValidateObj.validateString(bookingDTO.getCustomerEmail()) ) {
            booking.setCustomerEmail(bookingDTO.getCustomerEmail());
        }
        if ( ValidateObj.validateString(bookingDTO.getCustomerPhone()) ) {
            booking.setCustomerPhone(bookingDTO.getCustomerPhone());
        }
        if ( ValidateObj.validateBigDecimal(bookingDTO.getBookingPrice()) ) {
            booking.setBookingPrice(bookingDTO.getBookingPrice());
        }
        if ( ValidateObj.validateLocalDateTime(bookingDTO.getStartDate()) ) {
            booking.setStartDate(bookingDTO.getStartDate());
        }
        if ( ValidateObj.validateLocalDateTime(bookingDTO.getEndDate()) ) {
            booking.setEndDate(bookingDTO.getEndDate());
        }
        if ( ValidateObj.validateInt(bookingDTO.getStatus()) ) {
            booking.setStatus(bookingDTO.getStatus());
        }

        return bookingRepository.save(booking);
    }

}
