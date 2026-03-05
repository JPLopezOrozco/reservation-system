package com.pm.reservationsystem.repository;

import com.pm.reservationsystem.model.Policy;
import com.pm.reservationsystem.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {

    @EntityGraph(attributePaths = "schedules")
    Optional<Policy> findByRestaurantId(Long id);
}
