package com.pm.reservationsystem.repository;

import com.pm.reservationsystem.model.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulesRepository extends JpaRepository<Schedules, Long> {
}
