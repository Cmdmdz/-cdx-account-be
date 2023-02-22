package com.cdx.account.cdxaccountbe.repository;

import com.cdx.account.cdxaccountbe.repository.dao.Account;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
