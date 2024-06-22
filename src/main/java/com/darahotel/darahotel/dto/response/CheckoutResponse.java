package com.darahotel.darahotel.dto.response;

import com.darahotel.darahotel.entity.PaymentSlip;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckoutResponse {
    private PaymentSlip paymentSlip;
    private BookingResponse bookingResponse;
}
