package com.darahotel.darahotel.entity;

import com.darahotel.darahotel.dto.request.BookingDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "booking")
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "booking_price")
    private BigDecimal bookingPrice;

    @Column(name = "start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    @Column(name = "status")
    private int status;

    public Booking(BookingDTO bookingDTO) {
        this.customerName = bookingDTO.getCustomerName();
        this.customerEmail = bookingDTO.getCustomerEmail();
        this.customerPhone = bookingDTO.getCustomerPhone();
        this.customerAddress = bookingDTO.getCustomerAddress();
        this.bookingPrice = bookingDTO.getBookingPrice();
        this.startDate = bookingDTO.getStartDate();
        this.endDate = bookingDTO.getEndDate();
        this.status = bookingDTO.getStatus();
    }
}
