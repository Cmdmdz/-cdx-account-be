package com.cdx.account.cdxaccountbe.services;

import com.cdx.account.cdxaccountbe.model.response.HistoryResponse;
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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoryPaymentService {
    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;

    private final UserRepository userRepository;

    public ResponseEntity<?> execute(Long userId) {

        User  users = findByIdUser(userId);

        if (users == null) {
            return new ResponseEntity<>("Invalid Requested", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<Payment> payments;

        if ("admin".equals(users.getRole())) {
            payments = paymentRepository.findAll();
        } else {
            payments = paymentRepository.findAllByUserId(userId);
        }

        List<HistoryResponse> historyResponseList = payments.stream()
                .map(payment -> {

                    Optional<Account> accounts = accountRepository.findById(payment.getAccountId());

                    User userBypay = findByIdUser(payment.getUserId());
                    if (userBypay == null || accounts.isEmpty())
                        return HistoryResponse.builder().build();

                    Account account = accounts.get();

                    return HistoryResponse.builder()
                            .accountType(account.getAccountType())
                            .paymentId(payment.getPaymentId())
                            .amount(account.getAmount())
                            .isPayment(payment.getIsPayment() ? "Paid" : "Unpaid")
                            .updateDate(payment.getUpdateDate(
                            ))
                            .amountPaid(payment.getAmountPaid())
                            .username(userBypay.getUsername())
                            .visa(account.getVisa())
                            .build();

                }).toList();
        return new ResponseEntity<>(historyResponseList, HttpStatus.OK);
    }

    private User findByIdUser(Long userId) {
        Optional<User> users = userRepository.findById(userId);
        if (users.isEmpty())
            return null;

        return users.get();
    }
}
