package com.darahotel.darahotel.service.serviceImpl;

import com.darahotel.darahotel.dto.response.BookingResponse;
import com.darahotel.darahotel.dto.response.CheckoutResponse;
import com.darahotel.darahotel.entity.Booking;
import com.darahotel.darahotel.entity.PaymentSlip;
import com.darahotel.darahotel.exception.ResultNotFoundException;
import com.darahotel.darahotel.repository.PaymentSlipRepository;
import com.darahotel.darahotel.service.BookingListService;
import com.darahotel.darahotel.service.BookingService;
import com.darahotel.darahotel.service.PaymentService;
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


//        log.info("Booking List: {}", bookingResponse);
//        log.info("Booking: {}", booking);
//        log.info("Total Price: {}", totalPrice);
//        log.info("Payment Price: {}", paymentPrice);
//        log.info("Payment Object : {}", paymentSlip);

//        return checkoutResponse;
    }

    @Override
    public List<CheckoutResponse> getAllInvoices() {
        List<CheckoutResponse> responses = new ArrayList<CheckoutResponse>();
        List<PaymentSlip> paymentSlips = paymentSlipRepository.findAll();

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


}
