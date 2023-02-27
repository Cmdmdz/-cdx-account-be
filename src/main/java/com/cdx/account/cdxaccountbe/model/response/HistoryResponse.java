package com.cdx.account.cdxaccountbe.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryResponse {

    private Long paymentId;
    private String accountType;
    private Integer amount;
    private LocalDate updateDate;
    private String isPayment;
    private Integer amountPaid;
    private String visa;
    private String username;

}
