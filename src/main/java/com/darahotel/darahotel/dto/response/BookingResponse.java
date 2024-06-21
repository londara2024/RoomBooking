package com.darahotel.darahotel.dto.response;

import com.darahotel.darahotel.entity.Booking;
import com.darahotel.darahotel.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    Booking booking;
    List<Room> room;
}
