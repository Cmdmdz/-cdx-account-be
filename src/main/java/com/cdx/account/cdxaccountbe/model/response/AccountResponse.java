package com.cdx.account.cdxaccountbe.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private String accountType;
    private String about;
    private Long paymentId;
    private Integer amount;
    private String username;
}
