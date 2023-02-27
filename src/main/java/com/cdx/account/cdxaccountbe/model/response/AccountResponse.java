package com.cdx.account.cdxaccountbe.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private Long accountId;
    private String accountType;
    private String about;
    private Integer amount;
    private Long userId;
    private Integer amountPaid;
    private String visa;
    private String isPayment;
    private LocalDate updateDate;
    private String username;
}
