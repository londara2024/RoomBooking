package com.darahotel.darahotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "room_type")
@NoArgsConstructor
@AllArgsConstructor
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    @Column(name = "bed")
    private int bed;

    @Column(name = "bath")
    private int bath;

    @Column(name = "area")
    private String area;

    @Column(name = "price")
    private BigDecimal Price;

    @Column(name = "option")
    private String option;

    @Column(name = "status")
    private int status;
}
