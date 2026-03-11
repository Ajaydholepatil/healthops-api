package com.lending.healthops_api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRequestDto {

    private Long userId;
    private String firstName;
    private String lastName;
    private String title;
    private String gender;
    private String recordStatus;
    private String userType;

    private String password;
    private String authToken;

    private LocalDateTime created;
    private LocalDateTime updated;

    private String createdBy;
    private String updatedBy;

    private String barcode;
}
