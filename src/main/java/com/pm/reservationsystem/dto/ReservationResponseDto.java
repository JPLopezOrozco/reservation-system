package com.pm.reservationsystem.dto;

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
    private String restaurantName;
    private String tableName;
    private Instant startTime;
    private Instant expiryTime;
    private int partySize;
    private int durationMin;
    private String status;
    private String specialNotes;
    private String userEmail;
}
