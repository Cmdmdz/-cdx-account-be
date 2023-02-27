package com.cdx.account.cdxaccountbe.repository;

import com.cdx.account.cdxaccountbe.repository.dao.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUserId(Long userId);
}
