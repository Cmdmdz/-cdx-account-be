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

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> execute(Long userId) {
        User users = findByIdUser(userId);
        if (users == null) {
            return new ResponseEntity<>("Invalid Requested", HttpStatus.BAD_REQUEST);
        }

        List<Account> userList ;

        if ("admin".equals(users.getRole())) {
            userList = accountRepository.findAll();
        } else {
            userList = accountRepository.findAllByUserId(userId);
        }

        List<AccountResponse> accounts = userList
                .stream()
                .map(account -> {

                    User userByAccountUserId = findByIdUser(account.getUserId());

                    if (userByAccountUserId == null )
                        return AccountResponse.builder().build();

                    return AccountResponse.builder()
                            .accountId(account.getAccountId())
                            .username(userByAccountUserId.getUsername())
                            .accountType(account.getAccountType())
                            .amount(account.getAmount())
                            .about(account.getAbout())
                            .visa(account.getVisa())
                            .updateDate(account.getUpdateDate())
                            .isPayment(Boolean.TRUE.equals(account.getIsPayment()) ? "Paid" : "Unpaid")
                            .userId(account.getUserId())
                            .updateDate(account.getUpdateDate())
                            .amountPaid(account.getAmountPaid())
                            .build();
                }).toList();


        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    private User findByIdUser(Long userId) {
        Optional<User> users = userRepository.findById(userId);
        if (users.isEmpty())
            return null;

        return users.get();
    }
}
