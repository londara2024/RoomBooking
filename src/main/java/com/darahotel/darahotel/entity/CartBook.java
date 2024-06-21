package com.darahotel.darahotel.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cart_book")
public class CartBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Column(name = "message")
    private String mgs;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

}
