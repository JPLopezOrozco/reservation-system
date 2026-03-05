package com.pm.reservationsystem.mapper;

import com.pm.reservationsystem.dto.ReservationRequestDto;
import com.pm.reservationsystem.dto.ReservationResponseDto;
import com.pm.reservationsystem.model.Reservation;
import com.pm.reservationsystem.model.Restaurant;
import com.pm.reservationsystem.model.Table;
import com.pm.reservationsystem.model.User;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ReservationMapper {

    public ReservationResponseDto toReservationDto(Reservation reservation, Long restaurantId) {
        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
        reservationResponseDto.setId(reservation.getId());
        reservationResponseDto.setRestaurantId(restaurantId);
        reservationResponseDto.setTableId(reservation.getTable().getId());
        reservationResponseDto.setStartTime(reservation.getStartTime());
        reservationResponseDto.setExpiryTime(reservation.getExpiredAt());
        reservationResponseDto.setPartySize(reservation.getPartySize());
        reservationResponseDto.setDurationMin(reservation.getDurationMin());
        reservationResponseDto.setStatus(reservation.getStatus());
        reservationResponseDto.setSpecialNotes(reservation.getSpecialNotes());
        if (reservation.getUser() != null) {
            reservationResponseDto.setUserEmail(reservation.getUser().getEmail());
        }
        return reservationResponseDto;
    }

    public Reservation toReservation(ReservationRequestDto reservationRequestDto, Restaurant restaurant, Table table, int durationMin, User user, Instant expiryTime) {
        return Reservation.builder()
                .restaurant(restaurant)
                .table(table)
                .startTime(reservationRequestDto.getStartTime())
                .expiredAt(expiryTime)
                .partySize(reservationRequestDto.getPartySize())
                .durationMin(durationMin)
                .user(user)
                .specialNotes(reservationRequestDto.getSpecialNotes())
                .build();
    }

}
