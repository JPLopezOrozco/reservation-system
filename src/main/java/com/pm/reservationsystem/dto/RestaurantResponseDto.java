package com.pm.reservationsystem.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String address;
    private Set<TableResponseDto> tables;
}
