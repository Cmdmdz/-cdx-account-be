package com.cdx.account.cdxaccountbe.services;


import com.cdx.account.cdxaccountbe.model.request.RegisterRequest;
import com.cdx.account.cdxaccountbe.repository.UserRepository;
import com.cdx.account.cdxaccountbe.repository.dao.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;

    public ResponseEntity<?> execute(RegisterRequest request) {
        try {
            if (Boolean.TRUE.equals(userRepository.existsByUsername(request.getUsername()))) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            userRepository.save(User.builder()
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .build());
            return new ResponseEntity<>("Register successfully", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
