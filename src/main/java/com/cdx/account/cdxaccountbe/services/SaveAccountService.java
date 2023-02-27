package com.cdx.account.cdxaccountbe.services;

import com.cdx.account.cdxaccountbe.model.request.AccountRequest;
import com.cdx.account.cdxaccountbe.model.response.AccountResponse;
import com.cdx.account.cdxaccountbe.repository.AccountRepository;
import com.cdx.account.cdxaccountbe.repository.PaymentRepository;
import com.cdx.account.cdxaccountbe.repository.UserRepository;
import com.cdx.account.cdxaccountbe.repository.dao.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SaveAccountService {

    private final AccountRepository accountRepository;

    public ResponseEntity<?> execute(AccountRequest request, String userId) {

        var updateDate = LocalDate.now();

        accountRepository.save(Account.builder()
                .accountType(request.getAccountType())
                .amount(request.getAmount())
                .about(request.getAbout())
                .isPayment(Boolean.FALSE)
                .userId(Long.valueOf(userId))
                .amountPaid(0)
                .updateDate(updateDate)
                .build());

        return new ResponseEntity<>("Save Account successfully", HttpStatus.CREATED);

    }
}
