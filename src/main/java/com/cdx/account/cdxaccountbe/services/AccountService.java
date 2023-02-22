package com.cdx.account.cdxaccountbe.services;

import com.cdx.account.cdxaccountbe.model.request.AccountRequest;
import com.cdx.account.cdxaccountbe.model.response.AccountResponse;
import com.cdx.account.cdxaccountbe.repository.AccountRepository;
import com.cdx.account.cdxaccountbe.repository.PaymentRepository;
import com.cdx.account.cdxaccountbe.repository.UserRepository;
import com.cdx.account.cdxaccountbe.repository.dao.Account;
import com.cdx.account.cdxaccountbe.repository.dao.Payment;
import com.cdx.account.cdxaccountbe.repository.dao.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    public ResponseEntity<?> execute(AccountRequest request) {
        Optional<User> users = userRepository.findById(request.getUserId());
        Optional<Payment> payments = paymentRepository.findById(request.getPaymentId());
        Optional<Account> accounts = accountRepository.findById(request.getPaymentId());

        if (!(users.isPresent() && payments.isPresent() && accounts.isPresent())){
            return new  ResponseEntity<>("Invalid Requested", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Account account = accounts.get();
        User user = users.get();
        Payment payment = payments.get();
        return new  ResponseEntity<>(AccountResponse.builder()
                .accountType(account.getAccountType())
                .username(user.getUsername())
                .about(account.getAbout())
                .paymentId(payment.getPaymentId())
                .amount(account.getAmount())
                .build(), HttpStatus.OK);
    }
}
