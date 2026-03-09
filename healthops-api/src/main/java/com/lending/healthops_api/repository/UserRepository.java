package com.lending.healthops_api.repository;

import com.lending.healthops_api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = """
            SELECT * FROM user u
            WHERE (:firstName IS NULL OR SOUNDEX(u.first_name) = SOUNDEX(:firstName))
              AND (:userType IS NULL OR u.user_type = :userType)
              AND (:gender IS NULL OR u.gender = :gender)
            """, nativeQuery = true)
    List<UserEntity> searchUsers(String firstName, String gender, String userType);
}
