package com.lending.healthops_api.controller;

import com.lending.healthops_api.AppUtility.ApiResponse;
import com.lending.healthops_api.AppUtility.ApiResponseBuilder;
import com.lending.healthops_api.AppUtility.ApiResponseCode;
import com.lending.healthops_api.dto.UserRequestDto;
import com.lending.healthops_api.entity.UserEntity;
import com.lending.healthops_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/helthOps")
public class UserController {


    @Autowired
    UserService userService;


    //http://localhost:8080/helthOps/saveUsers
    @PostMapping("/saveUsers")
    public ResponseEntity<ApiResponse<UserEntity>> createUser(@RequestBody UserRequestDto userRequestDto) {

        UserEntity createUser = userService.createUser(userRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseBuilder.success(ApiResponseCode.MESSAGE_ID_001, ApiResponseCode.MESSAGE_001, createUser));
    }


    //http://localhost:8080/helthOps/searchUsers?userType=ADMIN
    @GetMapping("/searchUsers")
    public ResponseEntity<ApiResponse<List<UserEntity>>> searchUsers(@RequestParam(required = false) String firstName, @RequestParam(required = false) String gender, @RequestParam(required = false) String userType) {

        List<UserEntity> users = userService.searchUsers(firstName, gender, userType);

        return ResponseEntity.ok(ApiResponseBuilder.success(ApiResponseCode.MESSAGE_ID_002, ApiResponseCode.MESSAGE_002, users));
    }

    //http://localhost:8080/helthOps/users/1
    @PutMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<UserEntity>> updateUser(@PathVariable Long userId, @RequestBody UserRequestDto userRequestDto) {

        UserEntity updatedUser = userService.updateUser(userId, userRequestDto);

        return ResponseEntity.ok(ApiResponseBuilder.success(ApiResponseCode.MESSAGE_ID_003, ApiResponseCode.MESSAGE_003, updatedUser));
    }

    //http://localhost:8080/helthOps/users/2
    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<UserEntity>> getUserById(@PathVariable Long userId) {

        UserEntity user = userService.getUserById(userId);

        return ResponseEntity.ok(ApiResponseBuilder.success(ApiResponseCode.MESSAGE_ID_004, ApiResponseCode.MESSAGE_004, user));
    }


}

