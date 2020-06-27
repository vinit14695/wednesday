package com.wednesday.assignment.relaxicab.repository;

import com.wednesday.assignment.relaxicab.data.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

}
