package com.wednesday.assignment.relaxicab.repository;

import com.wednesday.assignment.relaxicab.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query
    User findByUsername(String userName);

    @Query
    User findByEmail(String email);
}
