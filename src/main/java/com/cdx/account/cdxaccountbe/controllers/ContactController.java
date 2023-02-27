package com.cdx.account.cdxaccountbe.controllers;

import com.cdx.account.cdxaccountbe.model.request.ContactRequest;
import com.cdx.account.cdxaccountbe.services.ContactService;
import com.cdx.account.cdxaccountbe.services.DeleteContactService;
import com.cdx.account.cdxaccountbe.services.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ContactController {

    private final ContactService contactService;
    private final FeedbackService feedbackService;

    private final DeleteContactService deleteContactService;

    @GetMapping("/contact")
    public ResponseEntity<?> findAllContact() {
        return contactService.execute();
    }

    @PostMapping("/contact/{userId}")
    public ResponseEntity<?> saveContact(@PathVariable Long userId, @RequestBody ContactRequest request) {
        return feedbackService.execute(userId, request);
    }

    @DeleteMapping("/contact/{contactId}")
    public ResponseEntity<?> deleteContact(@PathVariable Long contactId) {
        return deleteContactService.execute(contactId);
    }
}
