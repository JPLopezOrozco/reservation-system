package com.pm.reservationsystem.service;

import com.pm.reservationsystem.dto.PolicyResponseDto;
import com.pm.reservationsystem.exception.PolicyNotFoundException;
import com.pm.reservationsystem.mapper.PolicyMapper;
import com.pm.reservationsystem.model.Policy;
import com.pm.reservationsystem.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final PolicyMapper policyMapper;


    @Transactional(readOnly = true)
    public PolicyResponseDto getPolicyByRestaurantId(Long restaurantId) {
        Policy policy = policyRepository.findByRestaurantId(restaurantId)
                .orElseThrow(()-> new PolicyNotFoundException("Policy for restaurant id " + restaurantId + " not found"));

        return policyMapper.toPolicyResponseDto(policy);
    }


}
