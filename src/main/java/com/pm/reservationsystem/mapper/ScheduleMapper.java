package com.pm.reservationsystem.mapper;

import com.pm.reservationsystem.dto.SchedulesRequestDto;
import com.pm.reservationsystem.dto.SchedulesResponseDto;
import com.pm.reservationsystem.model.Policy;
import com.pm.reservationsystem.model.Schedules;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {

    public Schedules toSchedules(SchedulesRequestDto schedulesRequestDto, Policy policy) {
        return Schedules.builder()
                .policy(policy)
                .dayOfWeek(schedulesRequestDto.getDayOfWeek())
                .startTime(schedulesRequestDto.getStartTime())
                .endTime(schedulesRequestDto.getEndTime())
                .build();
    }

    public SchedulesResponseDto toSchedulesResponseDto(Schedules schedules) {
        return SchedulesResponseDto.builder()
                .id(schedules.getId())
                .policyId(schedules.getPolicy().getId())
                .dayOfWeek(schedules.getDayOfWeek())
                .startTime(schedules.getStartTime())
                .endTime(schedules.getEndTime())
                .build();
    }
}
