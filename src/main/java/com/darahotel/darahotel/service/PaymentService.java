package com.darahotel.darahotel.service;

import com.darahotel.darahotel.dto.response.CheckoutResponse;


import java.util.List;

public interface PaymentService {
    CheckoutResponse createPayment(Long bookingId);
    List<CheckoutResponse> getAllInvoices ();
}
