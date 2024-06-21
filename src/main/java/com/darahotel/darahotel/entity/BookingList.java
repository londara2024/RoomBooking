package com.darahotel.darahotel.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "booking_list")
@IdClass(BRL.class)
public class BookingList {
    @Id
    private Long bookingId;

    @Id
    private Long roomId;

    @Column(name = "status")
    private int status;
}
