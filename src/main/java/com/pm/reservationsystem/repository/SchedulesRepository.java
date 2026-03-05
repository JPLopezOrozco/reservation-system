package com.pm.reservationsystem.repository;

import com.pm.reservationsystem.model.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface SchedulesRepository extends JpaRepository<Schedules, Long> {
    @Query("""
        select (count (s) > 0 ) from Schedules s
        where s.policy.id = :policyId
        and s.dayOfWeek =:dayOfWeek
        and s.startTime < :endTime
        and s.endTime > :startTime
""")
    boolean existsOverlaping(Long policyId, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime);
    List<Schedules> findAllByPolicyId(Long policyId);
}
