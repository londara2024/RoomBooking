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

    @PostMapping("/invoices/{id}")
    public ResponseEntity<ApiBaseResponse<CheckoutResponse>> createInvoices(
            @PathVariable("id")  Long bookingId
    )
    {
        ApiBaseResponse<CheckoutResponse> response = new ApiBaseResponse<>();
        response.setData( paymentService.createPayment(bookingId));
        response.setStatus( HttpStatus.OK );
        response.setMessage( "success" );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/invoices/view")
    public ResponseEntity<ApiBaseResponse<List<CheckoutResponse>>> getAllInvoices() {
        ApiBaseResponse<List<CheckoutResponse>> response = new ApiBaseResponse<>();
        List<CheckoutResponse> paymentSlips = paymentService.getAllInvoices(null);
        response.setData(paymentSlips);
        response.setStatus(HttpStatus.OK);
        response.setMessage("success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/invoices/view/{payId}")
    public ResponseEntity<ApiBaseResponse<CheckoutResponse>> getByIdInvoices(
            @PathVariable(name = "payId", required = false) String payId
    ) {
        ApiBaseResponse<CheckoutResponse> response = new ApiBaseResponse<>();
        List<CheckoutResponse> paymentSlips = paymentService.getAllInvoices(payId);
        CheckoutResponse chkResponse = null;

        for (CheckoutResponse chkr: paymentSlips) {
            chkResponse = chkr;
        }

        response.setData(chkResponse);
        response.setStatus(HttpStatus.OK);
        response.setMessage("success");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/invoices/delete/{payId}")
    public ResponseEntity<ApiBaseResponse<PaymentSlip>> deleteInvoices(@PathVariable("payId") String payId){
        ApiBaseResponse<PaymentSlip> response = new ApiBaseResponse<PaymentSlip>();
        PaymentSlip paymentSlip = paymentService.deletePaymentSlip(payId);
        response.setData(paymentSlip);
        response.setStatus(HttpStatus.OK);
        response.setMessage("success");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/invoices/checkout/{payId}")
    public ResponseEntity<ApiBaseResponse<PaymentSlip>> checkoutInvoices(@PathVariable("payId") String payId){
        ApiBaseResponse<PaymentSlip> response = new ApiBaseResponse<PaymentSlip>();
        PaymentSlip paymentSlip = paymentService.updatePaymentSlip(payId);
        response.setData(paymentSlip);
        response.setStatus(HttpStatus.OK);
        response.setMessage("success");
        return ResponseEntity.ok(response);
    }
}
