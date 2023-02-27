package com.cdx.account.cdxaccountbe.services;

import com.cdx.account.cdxaccountbe.model.response.AccountResponse;
import com.cdx.account.cdxaccountbe.model.response.ContactResponse;
import com.cdx.account.cdxaccountbe.repository.ContactRepository;
import com.cdx.account.cdxaccountbe.repository.UserRepository;
import com.cdx.account.cdxaccountbe.repository.dao.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    public ResponseEntity<?> execute() {



        List<ContactResponse> accounts = contactRepository.findAll()
                .stream()
                .map(contact -> {
                    Optional<User> users = userRepository.findById(contact.getUserId());
                    if (users.isEmpty()) {
                        return ContactResponse.builder().build();
                    }

                    User user = users.get();

                    return ContactResponse.builder()
                            .contactId(contact.getContactId())
                            .description(contact.getDescription())
                            .createDate(contact.getCreateDate())
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .title(contact.getTitle())
                            .build();
                }).toList();


        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}
