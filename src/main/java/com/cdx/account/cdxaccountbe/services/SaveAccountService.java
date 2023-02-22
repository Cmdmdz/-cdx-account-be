package com.cdx.account.cdxaccountbe.services;

import com.cdx.account.cdxaccountbe.model.request.AccountRequest;
import com.cdx.account.cdxaccountbe.repository.AccountRepository;
import com.cdx.account.cdxaccountbe.repository.PaymentRepository;
import com.cdx.account.cdxaccountbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveAccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    public ResponseEntity<?> execute(AccountRequest request) {

    }
}
