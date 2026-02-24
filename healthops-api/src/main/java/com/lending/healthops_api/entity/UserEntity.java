package com.lending.healthops_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(length = 10)
    private String title;

    @Column(length = 1)
    private String gender;

    @Column(name = "record_status", length = 40)
    private String recordStatus;

    @Column(name = "user_type", length = 40)
    private String userType;

    @Column(length = 40)
    private String password;

    @Column(name = "auth_token", length = 200)
    private String authToken;

    private LocalDateTime created;
    private LocalDateTime updated;

    @Column(name = "created_by", length = 40)
    private String createdBy;

    @Column(name = "updated_by", length = 40)
    private String updatedBy;
}
