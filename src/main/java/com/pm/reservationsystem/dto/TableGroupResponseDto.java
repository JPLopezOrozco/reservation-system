package com.pm.reservationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableGroupResponseDto {
    private Long id;
    private Long restaurantId;
    private String name;

}
