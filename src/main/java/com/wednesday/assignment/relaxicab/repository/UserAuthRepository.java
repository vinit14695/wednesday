package com.wednesday.assignment.relaxicab.repository;

import com.wednesday.assignment.relaxicab.data.entity.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAuthRepository extends JpaRepository<UserAuthentication, Integer> {

    @Query
    UserAuthentication findByAccessToken(String accessToken);
}
