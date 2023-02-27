package com.cdx.account.cdxaccountbe.controllers;

import com.cdx.account.cdxaccountbe.model.request.AccountRequest;
import com.cdx.account.cdxaccountbe.model.request.PaymentPaidRequest;
import com.cdx.account.cdxaccountbe.model.request.RegisterRequest;
import com.cdx.account.cdxaccountbe.services.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AccountController {

    private final HistoryPaymentService historyPaymentService;
    private final AccountService accountService;
    private final SaveAccountService saveAccountService;

    private final DeleteAccountService deleteAccountService;
    private final UpdateAccountService updateAccountService;

    private final UpdatePaymentService updatePaymentService;

    @GetMapping("/accounts/{userId}")
    public ResponseEntity<?> findAllAccount(@PathVariable Long userId) {
        return accountService.execute(userId);
    }

    @PostMapping("/accounts")
    public ResponseEntity<?> saveAccount(@Valid @RequestBody AccountRequest request, @RequestParam String userId) {
        return saveAccountService.execute(request,userId);
    }

    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId) {
        return deleteAccountService.execute(accountId);
    }

    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<?> updateAccount(@Valid @RequestBody AccountRequest request, @PathVariable Long accountId) {
        return updateAccountService.execute(request,accountId);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<?> findAllHistory(@PathVariable Long userId) {
        return historyPaymentService.execute(userId);
    }

    @PutMapping("/payment/{accountId}")
    public ResponseEntity<?> updatePayment(@Valid @RequestBody PaymentPaidRequest request, @PathVariable Long accountId) {
        return updatePaymentService.execute(request,accountId);
    }
}
