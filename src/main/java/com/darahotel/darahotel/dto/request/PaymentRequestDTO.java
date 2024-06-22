package com.darahotel.darahotel.dto.request;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PaymentRequestDTO {

    private BigDecimal totalPrice;

    private LocalDateTime createdDate;

    private int status;

}
