package com.pm.reservationsystem.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PolicyRequestDto {
    private Set<SchedulesRequestDto> schedules;
}
