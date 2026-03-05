package com.pm.reservationsystem.service;

import com.pm.reservationsystem.dto.ReservationRequestDto;
import com.pm.reservationsystem.dto.ReservationResponseDto;
import com.pm.reservationsystem.exception.ReservationException;
import com.pm.reservationsystem.exception.ReservationNotFoundException;
import com.pm.reservationsystem.mapper.ReservationMapper;
import com.pm.reservationsystem.model.Reservation;
import com.pm.reservationsystem.model.Restaurant;
import com.pm.reservationsystem.model.Table;
import com.pm.reservationsystem.repository.ReservationRepository;
import com.pm.reservationsystem.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {


    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final TableRepository tableRepository;


    @Transactional
    public ReservationResponseDto createReservation(ReservationRequestDto reservationRequestDto) {
        Table table = tableRepository.findTableByIdForUpdate(reservationRequestDto.getTableId());

        int durationMin = 120;

        Restaurant restaurant = table.getRestaurant();
        ZoneId zoneId = ZoneId.of(restaurant.getTimeZone());
        Instant start = reservationRequestDto.getStartTime().atZone(zoneId).toInstant();
        Instant end = start.plus(Duration.ofMinutes(durationMin));

        if (reservationRepository.existsOverLapping(start, end, table.getId())){
            throw new ReservationException("Table not available for that time slot");
        }

        Instant expiredAt = Instant.now().plus(Duration.ofMinutes(30));

        Reservation reservation = reservationMapper.toReservation(reservationRequestDto, restaurant, table, durationMin, null, expiredAt);
        reservation.setStartTime(start);
        reservation.setEndTime(end);
        Reservation saved = reservationRepository.save(reservation);

        return reservationMapper.toReservationDto(saved, saved.getId());
    }

    @Transactional(readOnly = true)
    public ReservationResponseDto findReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(()-> new ReservationNotFoundException("Reservation not found"));
        return reservationMapper.toReservationDto(reservation, reservation.getTable().getRestaurant().getId());
    }

    @Transactional
    public void deleteReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(()-> new ReservationNotFoundException("Reservation not found"));
        reservationRepository.delete(reservation);
    }


}
