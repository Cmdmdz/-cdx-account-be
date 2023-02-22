package com.cdx.account.cdxaccountbe.model.response;

import com.cdx.account.cdxaccountbe.repository.dao.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryResponse {

    private Long paymentId;
    private String accountType;
    private Integer amount;
    private LocalDateTime createDate;
    private Boolean isPayment;
}
