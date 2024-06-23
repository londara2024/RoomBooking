package com.darahotel.darahotel.service;

import com.darahotel.darahotel.dto.response.CheckoutResponse;
import com.darahotel.darahotel.entity.PaymentSlip;


import java.util.List;
import java.util.Optional;

public interface PaymentService {
    CheckoutResponse createPayment(Long bookingId);
    List<CheckoutResponse> getAllInvoices (String payId);
    PaymentSlip deletePaymentSlip(String payId);
    PaymentSlip getPaymentSlipById (String payId);
    PaymentSlip updatePaymentSlip (String payId);
}
