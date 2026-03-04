package com.pm.reservationsystem.dto;

import com.pm.reservationsystem.model.Policy;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PolicyResponseDto {
    private Long id;
    private Long restaurantId;
}
