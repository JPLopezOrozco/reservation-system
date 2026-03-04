package com.pm.reservationsystem.mapper;

import com.pm.reservationsystem.dto.ReservationRequestDto;
import com.pm.reservationsystem.dto.ReservationResponseDto;
import com.pm.reservationsystem.model.Reservation;
import com.pm.reservationsystem.model.Restaurant;
import com.pm.reservationsystem.model.Table;
import com.pm.reservationsystem.model.User;

import java.time.Instant;

public class ReservationMapper {

    public ReservationResponseDto toReservationDto(Reservation reservation) {
        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
        reservationResponseDto.setRestaurantId(reservation.getRestaurant().getId());
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
