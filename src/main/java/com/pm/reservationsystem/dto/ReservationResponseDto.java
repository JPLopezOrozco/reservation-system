package com.pm.reservationsystem.dto;

import com.pm.reservationsystem.model.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResponseDto {
    private Long id;
    private Long restaurantId;
    private Long tableId;
    private Instant startTime;
    private Instant expiryTime;
    private Integer partySize;
    private Integer durationMin;
    private ReservationStatus status;
    private String specialNotes;
    private String userEmail;
}
