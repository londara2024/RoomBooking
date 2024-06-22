package com.darahotel.darahotel.controller;

import com.darahotel.darahotel.dto.response.ApiBaseResponse;
import com.darahotel.darahotel.dto.response.CheckoutResponse;
import com.darahotel.darahotel.entity.PaymentSlip;
import com.darahotel.darahotel.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PaymentRestController {

    private final PaymentService paymentService;

    @PutMapping("/invoices/{id}")
    public ResponseEntity<ApiBaseResponse<CheckoutResponse>> createInvoices(@PathVariable("id") Long bookingId) {
        ApiBaseResponse<CheckoutResponse> response = new ApiBaseResponse<>();
        response.setData( paymentService.createPayment(bookingId));
        response.setStatus( HttpStatus.OK );
        response.setMessage( "success" );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/invoices")
    public ResponseEntity<ApiBaseResponse<List<CheckoutResponse>>> getAllInvoices() {
        ApiBaseResponse<List<CheckoutResponse>> response = new ApiBaseResponse<>();
        List<CheckoutResponse> paymentSlips = paymentService.getAllInvoices();
        response.setData(paymentSlips);
        response.setStatus(HttpStatus.OK);
        response.setMessage("success");

        return ResponseEntity.ok(response);
    }

}
