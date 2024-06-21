package com.darahotel.darahotel.dto.request;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeDTO {
    private int bed;
    private int bath;
    private String area;
    private String option;
    private BigDecimal price;
    private int status;
}
