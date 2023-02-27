package com.cdx.account.cdxaccountbe.repository.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue
    private Long paymentId;
    private Integer amount;
    private Boolean isPayment;
    private LocalDate updateDate;
    private Long accountId;
    private Long userId;
    private Integer amountPaid;
    private String visa;

}
