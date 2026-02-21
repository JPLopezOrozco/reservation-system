package com.pm.reservationsystem.repository;

import com.pm.reservationsystem.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
