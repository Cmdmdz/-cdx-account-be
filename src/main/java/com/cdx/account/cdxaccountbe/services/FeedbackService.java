package com.cdx.account.cdxaccountbe.services;

import com.cdx.account.cdxaccountbe.model.request.ContactRequest;
import com.cdx.account.cdxaccountbe.repository.ContactRepository;
import com.cdx.account.cdxaccountbe.repository.UserRepository;
import com.cdx.account.cdxaccountbe.repository.dao.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final ContactRepository contactRepository;

    public ResponseEntity<?> execute(Long userId, ContactRequest request) {

        contactRepository.save(Contact.builder()
                        .userId(userId)
                        .title(request.getTitle())
                        .description(request.getDescription())
                        .createDate(LocalDate.now())
                .build());

        return new ResponseEntity<>("Contact to admin successfully", HttpStatus.CREATED);

    }
}
