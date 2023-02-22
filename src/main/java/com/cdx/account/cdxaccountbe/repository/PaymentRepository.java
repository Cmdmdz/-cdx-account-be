package com.cdx.account.cdxaccountbe.repository;

import com.cdx.account.cdxaccountbe.repository.dao.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
