package com.darahotel.darahotel.entity;

import com.darahotel.darahotel.utils.GenerateCodePayment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payment_slip")
public class PaymentSlip {

    public PaymentSlip () {
        paymentGeneration();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "payment_price")
    private BigDecimal paymentPrice;

    @Column(name = "total_rooms")
    private int totalRooms;

    @Column(name = "total_date")
    private int totalDate;

    @Column(name = "created_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @Column(name = "status")
    private int status;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private void paymentGeneration () {
        //generate payment
        GenerateCodePayment paymentCode = new GenerateCodePayment();
        setPaymentId(paymentCode.paymentCode());
    }
}
