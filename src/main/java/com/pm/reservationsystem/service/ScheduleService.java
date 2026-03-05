package com.pm.reservationsystem.service;

import com.pm.reservationsystem.dto.SchedulesRequestDto;
import com.pm.reservationsystem.dto.SchedulesResponseDto;
import com.pm.reservationsystem.exception.PolicyNotFoundException;
import com.pm.reservationsystem.exception.ScheduleException;
import com.pm.reservationsystem.exception.ScheduleNotFoundException;
import com.pm.reservationsystem.mapper.ScheduleMapper;
import com.pm.reservationsystem.model.Policy;
import com.pm.reservationsystem.model.Schedules;
import com.pm.reservationsystem.repository.PolicyRepository;
import com.pm.reservationsystem.repository.SchedulesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {


    private final SchedulesRepository schedulesRepository;
    private final PolicyRepository policyRepository;
    private final ScheduleMapper scheduleMapper;

    @Transactional
    public SchedulesResponseDto addSchedule(SchedulesRequestDto schedulesRequestDto, Long policyId) {
        if (schedulesRepository.existsOverlaping(policyId, schedulesRequestDto.getDayOfWeek(), schedulesRequestDto.getStartTime(), schedulesRequestDto.getEndTime())) {
            throw new ScheduleException("Schedule already exists");
        }

        if (schedulesRequestDto.getStartTime().isAfter(schedulesRequestDto.getEndTime())) {
            throw new ScheduleException("Start time cannot be after end time");
        }
        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new PolicyNotFoundException("Policy not found"));

        Schedules newSchedule = scheduleMapper.toSchedules(schedulesRequestDto, policy);
        schedulesRepository.save(newSchedule);

        log.info("New schedule: {}", newSchedule);

        return scheduleMapper.toSchedulesResponseDto(newSchedule);
    }

    @Transactional(readOnly = true)
    public List<SchedulesResponseDto> getSchedules(Long policyId) {

        return schedulesRepository.findAllByPolicyId(policyId).stream()
                .map(scheduleMapper::toSchedulesResponseDto)
                .toList();
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {

        Schedules schedule = schedulesRepository.findById(scheduleId)
                        .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found"));
        schedulesRepository.delete(schedule);
        log.info("Schedule with id {} was deleted", scheduleId);

    }




}
