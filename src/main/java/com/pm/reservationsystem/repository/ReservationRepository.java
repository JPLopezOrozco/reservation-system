package com.pm.reservationsystem.repository;

import com.pm.reservationsystem.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
        select (count(r) > 0) from Reservation r
        where r.table.id = :tableId
        and r.status in (com.pm.reservationsystem.model.ReservationStatus.HOLD,
                         com.pm.reservationsystem.model.ReservationStatus.SEATED,
                         com.pm.reservationsystem.model.ReservationStatus.CONFIRMED)
        and r.startTime < :endTime
        and r.endTime  > :startTime
""")
    boolean existsOverLapping(@Param("startTime") Instant startTime,
                              @Param("endTime") Instant endTime,
                              @Param("tableId") Long tableId);


}
