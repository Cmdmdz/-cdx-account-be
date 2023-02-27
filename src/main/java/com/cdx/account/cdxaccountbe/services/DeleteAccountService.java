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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteAccountService {
    private final AccountRepository accountRepository;

    public ResponseEntity<?> execute(Long accountId) {


        if (accountId == null){
            return new ResponseEntity<>("Invalid Requested accountId", HttpStatus.BAD_REQUEST);
        }

        accountRepository.deleteById(accountId);

        return new ResponseEntity<>("Deleted Account successfully", HttpStatus.OK);

    }
}
