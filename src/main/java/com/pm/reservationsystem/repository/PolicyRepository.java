package com.pm.reservationsystem.repository;

import com.pm.reservationsystem.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
}
