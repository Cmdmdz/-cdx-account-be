package com.cdx.account.cdxaccountbe.services;

import com.cdx.account.cdxaccountbe.model.request.AccountRequest;
import com.cdx.account.cdxaccountbe.model.request.PaymentPaidRequest;
import com.cdx.account.cdxaccountbe.repository.AccountRepository;
import com.cdx.account.cdxaccountbe.repository.PaymentRepository;
import com.cdx.account.cdxaccountbe.repository.dao.Account;
import com.cdx.account.cdxaccountbe.repository.dao.Payment;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdatePaymentService {
    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;

    public ResponseEntity<?> execute(PaymentPaidRequest request, Long accountId) {

        Optional<Account> accounts = accountRepository.findById(accountId);

        if (accounts.isEmpty()) {
            return new ResponseEntity<>("Invalid Requested", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Account account = accounts.get();

        accountRepository.save(Account.builder()
                .accountId(account.getAccountId())
                .accountType(account.getAccountType())
                .amount(account.getAmount())
                .about(account.getAbout())
                .isPayment(request.getAmountPaid() >= account.getAmount())
                .amountPaid(account.getAmountPaid())
                .visa(request.getVisa())
                .userId(account.getUserId())
                .amountPaid(request.getAmountPaid())
                .updateDate(LocalDate.now())
                .build());

        paymentRepository.save(Payment.builder()
                        .accountId(account.getAccountId())
                        .updateDate(LocalDate.now())
                        .isPayment(request.getAmountPaid() >= account.getAmount())
                        .amount(account.getAmount())
                        .amountPaid(request.getAmountPaid())
                        .visa(request.getVisa())
                        .userId(account.getUserId())
                .build());

        return new ResponseEntity<>("Updated Payment paid successfully", HttpStatus.OK);

    }
}
