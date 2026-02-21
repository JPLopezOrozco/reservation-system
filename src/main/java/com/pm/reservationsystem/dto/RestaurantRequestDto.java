package com.pm.reservationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RestaurantRequestDto {
    private String name;
    private String address;
    private String timezone;
    private PolicyRequestDto policy;
}
