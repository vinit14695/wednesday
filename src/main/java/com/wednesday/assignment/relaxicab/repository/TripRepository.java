package com.wednesday.assignment.relaxicab.repository;

import com.wednesday.assignment.relaxicab.data.entity.Trip;
import com.wednesday.assignment.relaxicab.data.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

    @Query
    List<Trip> findByUser(User user, Sort sort);
}
