package com.pm.reservationsystem.mapper;

import com.pm.reservationsystem.dto.ReservationResponseDto;
import com.pm.reservationsystem.model.Reservation;

public class ReservationMapper {

    public ReservationResponseDto toReservationDto(Reservation reservation) {
        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
        reservationResponseDto.setRestaurantName(reservation.getRestaurant().getName());
        reservationResponseDto.setTableName(reservation.getTable().getTableName());
        reservationResponseDto.setStartTime(reservation.getStartTime());
        reservationResponseDto.setExpiryTime(reservation.getExpiredAt());
        reservationResponseDto.setPartySize(reservation.getPartySize());
        reservationResponseDto.setDurationMin(reservation.getDurationMin());
        reservationResponseDto.setStatus(reservation.getStatus().toString());
        reservationResponseDto.setSpecialNotes(reservation.getSpecialNotes());
        reservationResponseDto.setUserEmail(reservation.getUser().getEmail());
        return reservationResponseDto;
    }

}
