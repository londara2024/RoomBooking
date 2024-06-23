package com.darahotel.darahotel.service.serviceImpl;

import com.darahotel.darahotel.dto.request.BookingDTO;
import com.darahotel.darahotel.dto.request.RoomDTO;
import com.darahotel.darahotel.dto.response.BookingResponse;
import com.darahotel.darahotel.dto.response.CheckoutResponse;
import com.darahotel.darahotel.entity.Booking;
import com.darahotel.darahotel.entity.PaymentSlip;
import com.darahotel.darahotel.entity.Room;
import com.darahotel.darahotel.exception.ResultNotFoundException;
import com.darahotel.darahotel.repository.PaymentSlipRepository;
import com.darahotel.darahotel.service.BookingListService;
import com.darahotel.darahotel.service.BookingService;
import com.darahotel.darahotel.service.PaymentService;
import com.darahotel.darahotel.service.RoomService;
import com.darahotel.darahotel.utils.PaymentCalculation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Data
@RequiredArgsConstructor
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentSlipRepository paymentSlipRepository;
    private final BookingListService bookingListService;
    private final BookingService bookingService;
    private final RoomService roomService;
    @Override
    public CheckoutResponse createPayment(Long bookingId) {
        BookingResponse bookingResponse = bookingListService.getBookingById(bookingId);
        Booking booking = bookingService.getBookingById(bookingId);

        PaymentCalculation paymentCalculation = new PaymentCalculation(bookingResponse);
        Double totalPrice = paymentCalculation.totalPrice();
        Double paymentPrice = paymentCalculation.amountOfBooking();
        Integer totalDate = paymentCalculation.getCalculateDate();
        Integer totalRooms = paymentCalculation.getTotalRooms();

        PaymentSlip paymentSlip = new PaymentSlip();
        paymentSlip.setTotalPrice(BigDecimal.valueOf(totalPrice));
        paymentSlip.setPaymentPrice(BigDecimal.valueOf(paymentPrice));
        paymentSlip.setTotalDate(totalDate);
        paymentSlip.setTotalRooms(totalRooms);
        paymentSlip.setBooking(booking);
        paymentSlip.setCreatedDate(LocalDateTime.now());
        paymentSlip.setStatus(1);

        paymentSlip = paymentSlipRepository.save(paymentSlip);

        return CheckoutResponse.builder()
                .paymentSlip(paymentSlip)
                .bookingResponse(bookingResponse)
                .build();

    }

    @Override
    public List<CheckoutResponse> getAllInvoices(String payId) {
        List<CheckoutResponse> responses = new ArrayList<CheckoutResponse>();
        List<PaymentSlip> paymentSlips = new ArrayList<>();

        if (payId == null) {
            paymentSlips = paymentSlipRepository.findAll();
        } else {
            PaymentSlip paymentSlip =  getPaymentSlipById(payId);
            paymentSlips.add(paymentSlip);
        }

        for (PaymentSlip paymentSlip : paymentSlips) {

            // Get list of booking
            BookingResponse bookingResponse = bookingListService.getBookingById(
                    paymentSlip.getBooking().getBookingId()
            );

            CheckoutResponse checkoutResponse = CheckoutResponse.builder()
                    .bookingResponse(bookingResponse)
                    .paymentSlip(paymentSlip)
                    .build();
            responses.add(checkoutResponse);
        }

        return responses;
    }

    @Override
    public PaymentSlip deletePaymentSlip(String payId) {
        PaymentSlip paymentSlip = getPaymentSlipById(payId);
        paymentSlipRepository.delete(paymentSlip);
        return paymentSlip;
    }

    @Override
    public PaymentSlip getPaymentSlipById(String payId) {
        return paymentSlipRepository.findByPaymentId(payId).orElseThrow(
                        () -> new ResultNotFoundException("PaymentSlip", String.valueOf(payId))
                );
    }

    @Override
    public PaymentSlip updatePaymentSlip(String payId) {

        PaymentSlip paymentSlip = getPaymentSlipById(payId);
        Booking booking = bookingService.getBookingById(paymentSlip.getBooking().getBookingId());
        BookingResponse bookingList = bookingListService.getBookingById(booking.getBookingId());
        List<Room> roomList = bookingList.getRoom();

        // TODO : update status in table payment
        paymentSlip.setStatus(2);
        paymentSlipRepository.save(paymentSlip);

        // TODO: update status in table booking
        booking.setStatus(2);
        BookingDTO bookingDTO = new BookingDTO(booking);
        bookingService.updateBooking(booking.getBookingId(), bookingDTO);

        // TODO: update status in table room
        for (Room room: roomList) {
            room.setStatus(1);
            RoomDTO roomDTO = new RoomDTO(room);
            roomService.updateRoom(room.getRoomId(), roomDTO);
        }

//        log.info("PaymentSlip updated: {}" , paymentSlip);
//        log.info("Booking updated: {}" , booking);
//        log.info("Room List :: {}", roomList);
        return paymentSlip;
    }


}
