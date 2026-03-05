package com.pm.reservationsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDto {
    @NotNull
    private Long tableId;
    @NotNull @Min(1)
    private Integer partySize;
    @NotNull
    private Instant startTime;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String specialNotes;
}
