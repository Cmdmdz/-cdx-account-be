package com.cdx.account.cdxaccountbe.services;

import com.cdx.account.cdxaccountbe.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteContactService {

    private final ContactRepository contactRepository;

    public ResponseEntity<?> execute(Long contactId) {


        if (contactId == null){
            return new ResponseEntity<>("Invalid Requested accountId", HttpStatus.BAD_REQUEST);
        }
        contactRepository.deleteById(contactId);
        return new ResponseEntity<>("Deleted contact successfully", HttpStatus.OK);

    }
}
