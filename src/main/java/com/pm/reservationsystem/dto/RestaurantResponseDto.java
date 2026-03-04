package com.pm.reservationsystem.dto;

import lombok.*;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String address;
    private Instant createdAt;
}
