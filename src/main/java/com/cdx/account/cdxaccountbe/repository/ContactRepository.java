package com.cdx.account.cdxaccountbe.repository;

import com.cdx.account.cdxaccountbe.repository.dao.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
