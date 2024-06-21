package com.darahotel.darahotel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingListRequestDTO {
    private Long bookingId;
    private List<Long> roomId;
//    private int status;
}
