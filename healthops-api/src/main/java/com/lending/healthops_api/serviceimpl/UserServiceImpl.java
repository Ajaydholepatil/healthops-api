package com.lending.healthops_api.serviceimpl;

import com.lending.healthops_api.dto.UserRequestDto;
import com.lending.healthops_api.entity.UserEntity;
import com.lending.healthops_api.repository.UserRepository;
import com.lending.healthops_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity createUser(UserRequestDto userRequestDto) {
        UserEntity user = new UserEntity();
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setTitle(userRequestDto.getTitle());
        user.setGender(userRequestDto.getGender());
        user.setUserType(userRequestDto.getUserType());
        user.setRecordStatus(userRequestDto.getRecordStatus());
        user.setPassword(userRequestDto.getPassword());
        user.setAuthToken(UUID.randomUUID().toString());

        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
        user.setCreatedBy(userRequestDto.getCreatedBy());
        user.setUpdatedBy(userRequestDto.getCreatedBy());

        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> searchUsers(String firstName, String userType) {
        return userRepository.searchUsers(firstName, userType);
    }

    @Override
    public UserEntity updateUser(Long userId, UserRequestDto userRequestDto) {
        UserEntity existingUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Update only required fields
        existingUser.setFirstName(userRequestDto.getFirstName());
        existingUser.setLastName(userRequestDto.getLastName());
        existingUser.setTitle(userRequestDto.getTitle());
        existingUser.setGender(userRequestDto.getGender());
        existingUser.setUserType(userRequestDto.getUserType());
        existingUser.setRecordStatus(userRequestDto.getRecordStatus());
        existingUser.setPassword(userRequestDto.getPassword());

        existingUser.setUpdated(LocalDateTime.now());
        existingUser.setUpdatedBy(userRequestDto.getUpdatedBy());

        return userRepository.save(existingUser);
    }

    @Override
    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }
}
