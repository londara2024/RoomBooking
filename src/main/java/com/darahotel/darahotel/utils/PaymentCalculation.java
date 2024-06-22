package com.darahotel.darahotel.utils;

import com.darahotel.darahotel.dto.response.BookingResponse;
import com.darahotel.darahotel.entity.Room;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.util.List;

@Slf4j
@AllArgsConstructor
public class PaymentCalculation {

    private BookingResponse bookingResponse;

    /*
    * TODO: calculate for amount of payment
    * */
    public Double amountOfBooking () {
        Double bookingPrice = bookingResponse.getBooking().getBookingPrice().doubleValue();
        Double ttPrice = totalPrice();
        Double pay = ttPrice - bookingPrice;
        log.info("Total of payment: {}", pay);
        return pay;
    }

    /*
    * TODO: Total all of room multiply with amount of date.
    * */
    public Double totalPrice() {
        Integer totalDate = getCalculateDate();
        Double price = getCalculatePrice();
        Double sum = totalDate * price;
        log.info("Total Room and Price: {}", sum);
        return sum;
    }

    /*
    * TODO: Calculate Date Time
    * */
    public Integer getCalculateDate() {
        Integer date =  DateValidation.calculateDate(
                bookingResponse.getBooking().getStartDate(),
                bookingResponse.getBooking().getEndDate()
        );
        log.info("Total Date: {}", date);
        return date;
    }

    /*
    * TODO: Calculate room price in booking rooms
    * */
    public Double getCalculatePrice() {
        List<Room> roomList = bookingResponse.getRoom();
        Double price = 0.0;
        for ( Room room : roomList ) {
            price += room.getRoomType().getPrice().doubleValue();
        }
        log.info("Total sum room price : {}", price);
        return price;
    }

    public Integer getTotalRooms () {
        Integer totalRoom = bookingResponse.getRoom().size();
        log.info("Total Room: {}", totalRoom);
        return totalRoom;
    }

}
