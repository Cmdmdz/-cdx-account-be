package com.cdx.account.cdxaccountbe.services;

import com.cdx.account.cdxaccountbe.model.request.AccountRequest;
import com.cdx.account.cdxaccountbe.repository.AccountRepository;
import com.cdx.account.cdxaccountbe.repository.dao.Account;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UpdateAccountService {
    private final AccountRepository accountRepository;

    public ResponseEntity<?> execute(AccountRequest request, Long accountId) {

        var updateDate = LocalDate.now();

        Optional<Account> accounts = accountRepository.findById(accountId);

        if (accounts.isEmpty()) {
            return new ResponseEntity<>("Invalid Requested", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Account account = accounts.get();
        var isPayment = account.getIsPayment();
        if (StringUtils.isNotBlank(request.getIsPayment()) && Objects.equals(request.getIsPayment(),"Unpaid")){
            isPayment = false;
        }

        accountRepository.save(Account.builder()
                .accountId(account.getAccountId())
                .accountType(StringUtils.isNotBlank(request.getAccountType()) ? request.getAccountType() : account.getAccountType())
                .amount(request.getAmount() != null ? request.getAmount() : account.getAmount())
                .about(StringUtils.isNotBlank(request.getAbout()) ? request.getAbout() : account.getAbout())
                .isPayment(isPayment)
                .amountPaid(isPayment ? account.getAmountPaid() : 0)
                .visa(isPayment ? account.getVisa() : null)
                .userId(account.getUserId())
                .updateDate(LocalDate.now())
                .build());

        return new ResponseEntity<>("Updated Account successfully", HttpStatus.OK);

    }
}
