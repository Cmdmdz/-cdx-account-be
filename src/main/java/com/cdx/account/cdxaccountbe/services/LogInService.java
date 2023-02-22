package com.cdx.account.cdxaccountbe.services;


import com.cdx.account.cdxaccountbe.model.request.LogInRequest;
import com.cdx.account.cdxaccountbe.model.response.LogInResponse;
import com.cdx.account.cdxaccountbe.repository.UserRepository;
import com.cdx.account.cdxaccountbe.repository.dao.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class LogInService {
    private final UserRepository userRepository;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final Base64.Encoder ENCODER = Base64.getUrlEncoder();

    public ResponseEntity<?> execute(LogInRequest request) {
        Boolean isUsernamePasswordMatch = userRepository.existsByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (Boolean.TRUE.equals(isUsernamePasswordMatch)) {
            byte[] TOKEN = new byte[24];
            SECURE_RANDOM.nextBytes(TOKEN);
            User user = userRepository.findByUsername(request.getUsername());
            LogInResponse response = LogInResponse.builder().userId(user.getUserId()).username(user.getUsername()).token(ENCODER.encodeToString(TOKEN)).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Username or password is incorrect",HttpStatus.NOT_FOUND);
        }
    }
}
