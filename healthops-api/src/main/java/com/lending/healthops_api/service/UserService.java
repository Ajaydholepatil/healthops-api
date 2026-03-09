package com.lending.healthops_api.service;

import com.lending.healthops_api.dto.UserRequestDto;
import com.lending.healthops_api.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserEntity createUser(UserRequestDto userRequestDto);

    List<UserEntity> searchUsers(String firstName,String gender, String userType);

    UserEntity updateUser(Long userId, UserRequestDto userRequestDto);

    UserEntity getUserById(Long userId);
}
