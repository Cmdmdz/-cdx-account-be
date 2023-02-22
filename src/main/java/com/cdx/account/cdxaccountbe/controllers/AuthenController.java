package com.cdx.account.cdxaccountbe.controllers;


import com.cdx.account.cdxaccountbe.model.request.LogInRequest;
import com.cdx.account.cdxaccountbe.model.request.RegisterRequest;
import com.cdx.account.cdxaccountbe.repository.dao.User;
import com.cdx.account.cdxaccountbe.services.LogInService;
import com.cdx.account.cdxaccountbe.services.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthenController {

    private final RegisterService registerService;
    private final LogInService logInService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        return registerService.execute(request);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login (@Valid @RequestBody LogInRequest request) {
        return logInService.execute(request);
    }
}
