package com.cdx.account.cdxaccountbe.repository;

import com.cdx.account.cdxaccountbe.repository.dao.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByUserId(Long userId);
}
