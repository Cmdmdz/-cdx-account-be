package com.cdx.account.cdxaccountbe.repository.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @GeneratedValue
    @Id
    private Long contactId;
    private Long userId;
    private String title;
    private String description;
    private LocalDate createDate;
}
