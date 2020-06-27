package com.wednesday.assignment.relaxicab.repository;

import com.wednesday.assignment.relaxicab.data.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
}
