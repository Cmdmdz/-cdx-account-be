package com.cdx.account.cdxaccountbe.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponse {
    private Long contactId;
    private String email;
    private String username;
    private String description;
    private LocalDate createDate;
    private String title;
}
