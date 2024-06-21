package com.darahotel.darahotel.dto.request;

import com.darahotel.darahotel.entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;
    private BigDecimal bookingPrice;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int status;

    public BookingDTO(Booking booking) {
        this.customerName = booking.getCustomerName();
        this.customerEmail = booking.getCustomerEmail();
        this.customerPhone = booking.getCustomerPhone();
        this.customerAddress = booking.getCustomerAddress();
        this.bookingPrice = booking.getBookingPrice();
        this.startDate = booking.getStartDate();
        this.endDate = booking.getEndDate();
        this.status = booking.getStatus();
    }
}
