package com.lending.healthops_api;

import com.lending.healthops_api.entity.UserEntity;
import com.lending.healthops_api.repository.UserRepository;
import com.lending.healthops_api.serviceimpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserEntity user1;
    private UserEntity user2;


    @Test
    void searchUsers_AllScenarios() {

        // ---- Setup ----
        UserEntity user1 = new UserEntity();
        user1.setUserId(1L);
        user1.setFirstName("John");
        user1.setGender("Male");
        user1.setUserType("Admin");

        UserEntity user2 = new UserEntity();
        user2.setUserId(2L);
        user2.setFirstName("Jane");
        user2.setGender("Female");
        user2.setUserType("User");

        // ---- Test 1: All params provided ----
        when(userRepository.searchUsers("John", "Male", "Admin")).thenReturn(List.of(user1));
        List<UserEntity> result1 = userService.searchUsers("John", "Male", "Admin");
        assertNotNull(result1);
        assertEquals(1, result1.size());
        assertEquals("John", result1.get(0).getFirstName());
        assertEquals("Male", result1.get(0).getGender());
        assertEquals("Admin", result1.get(0).getUserType());
        System.out.println("Test 1 PASSED - All params matched: " + result1.get(0).getFirstName() + " | " + result1.get(0).getGender() + " | " + result1.get(0).getUserType());

        // ---- Test 2: Null firstName ----
        when(userRepository.searchUsers(null, "Male", "Admin")).thenReturn(List.of(user1));
        List<UserEntity> result2 = userService.searchUsers(null, "Male", "Admin");
        assertNotNull(result2);
        assertEquals(1, result2.size());
        System.out.println("Test 2 PASSED - Null firstName returned: " + result2.size() + " user");

        // ---- Test 3: All null params ----
        when(userRepository.searchUsers(null, null, null)).thenReturn(Arrays.asList(user1, user2));
        List<UserEntity> result3 = userService.searchUsers(null, null, null);
        assertNotNull(result3);
        assertEquals(2, result3.size());
        System.out.println("Test 3 PASSED - All nulls returned: " + result3.size() + " users");

        // ---- Test 4: No match found ----
        when(userRepository.searchUsers("Unknown", "Male", "Admin")).thenReturn(Collections.emptyList());
        List<UserEntity> result4 = userService.searchUsers("Unknown", "Male", "Admin");
        assertNotNull(result4);
        assertTrue(result4.isEmpty());
        System.out.println("Test 4 PASSED - No match found, returned empty list: " + result4.size() + " users");

        // ---- Test 5: Soundex similar name (Jon -> John) ----
        when(userRepository.searchUsers("Jon", "Male", "Admin")).thenReturn(List.of(user1));
        List<UserEntity> result5 = userService.searchUsers("Jon", "Male", "Admin");
        assertNotNull(result5);
        assertEquals(1, result5.size());
        assertEquals("John", result5.get(0).getFirstName());
        System.out.println("Test 5 PASSED - Soundex 'Jon' matched: " + result5.get(0).getFirstName());

        // ---- Test 6: Only gender filter ----
        when(userRepository.searchUsers(null, "Female", null)).thenReturn(List.of(user2));
        List<UserEntity> result6 = userService.searchUsers(null, "Female", null);
        assertNotNull(result6);
        assertEquals(1, result6.size());
        assertEquals("Female", result6.get(0).getGender());
        System.out.println("Test 6 PASSED - Gender filter returned: " + result6.get(0).getFirstName() + " | " + result6.get(0).getGender());

        // ---- Test 7: Only userType filter ----
        when(userRepository.searchUsers(null, null, "User")).thenReturn(List.of(user2));
        List<UserEntity> result7 = userService.searchUsers(null, null, "User");
        assertNotNull(result7);
        assertEquals(1, result7.size());
        assertEquals("User", result7.get(0).getUserType());
        System.out.println("Test 7 PASSED - UserType filter returned: " + result7.get(0).getFirstName() + " | " + result7.get(0).getUserType());

        // ---- Test 8: Repository throws exception ----
        when(userRepository.searchUsers("John", "Male", "Admin")).thenThrow(new RuntimeException("Database error"));
        Exception exception = assertThrows(RuntimeException.class, () -> userService.searchUsers("John", "Male", "Admin"));
        System.out.println("Test 8 PASSED - Exception caught: " + exception.getMessage());

        // ---- Verify all repository calls ----
        verify(userRepository, times(1)).searchUsers("Jon", "Male", "Admin");
        verify(userRepository, times(1)).searchUsers(null, "Female", null);
        verify(userRepository, times(1)).searchUsers(null, null, "User");
        verify(userRepository, times(1)).searchUsers(null, "Male", "Admin");
        verify(userRepository, times(1)).searchUsers(null, null, null);
        verify(userRepository, times(1)).searchUsers("Unknown", "Male", "Admin");
        verify(userRepository, times(2)).searchUsers("John", "Male", "Admin");


    }


}
