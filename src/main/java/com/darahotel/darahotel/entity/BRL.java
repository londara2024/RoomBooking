package com.darahotel.darahotel.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BRL implements Serializable {
    private Long bookingId;
    private Long roomId;
}
