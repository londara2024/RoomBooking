package com.darahotel.darahotel.repository;

import com.darahotel.darahotel.entity.PaymentSlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentSlipRepository extends JpaRepository<PaymentSlip, Long> {
    Optional<PaymentSlip> findByPaymentId(String paymentId);
}
