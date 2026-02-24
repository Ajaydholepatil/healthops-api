package com.lending.healthops_api.repository;

import com.lending.healthops_api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("""
                SELECT u FROM UserEntity u
                WHERE (:firstName IS NULL OR u.firstName = :firstName)
                  AND (:userType IS NULL OR u.userType = :userType)
            """)
    List<UserEntity> searchUsers(String firstName, String userType);
}
