package com.pm.reservationsystem.controller;

import com.pm.reservationsystem.dto.SchedulesRequestDto;
import com.pm.reservationsystem.dto.SchedulesResponseDto;
import com.pm.reservationsystem.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {


    private final ScheduleService scheduleService;


    @PostMapping("/policies/{policyId}/schedules")
    public ResponseEntity<SchedulesResponseDto> createSchedule(@Valid @RequestBody SchedulesRequestDto schedulesRequestDto, @PathVariable Long policyId){
        SchedulesResponseDto schedulesResponseDto = scheduleService.addSchedule(schedulesRequestDto, policyId);
        var location = UriComponentsBuilder
                .fromPath("/schedule/{policyId}")
                .buildAndExpand(schedulesResponseDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(schedulesResponseDto);
    }

    @GetMapping("/policies/{policyId}/schedules")
    public ResponseEntity<List<SchedulesResponseDto>> getSchedule(@PathVariable Long policyId){
        List<SchedulesResponseDto> schedulesResponseDto = scheduleService.getSchedules(policyId);
        return ResponseEntity.ok(schedulesResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id){
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

}
