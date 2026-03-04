package com.pm.reservationsystem.dto;

import com.pm.reservationsystem.model.Restaurant;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RestaurantRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String timezone;
}
