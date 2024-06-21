package com.darahotel.darahotel.repository;

import com.darahotel.darahotel.entity.PaymentSlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSlipRepository extends JpaRepository<PaymentSlip, Long> {
}
