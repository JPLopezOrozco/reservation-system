package com.pm.reservationsystem.mapper;

import com.pm.reservationsystem.dto.PolicyResponseDto;
import com.pm.reservationsystem.model.Policy;

public class PolicyMapper {


    public PolicyResponseDto toPolicyResponseDto(Policy policy) {
        return PolicyResponseDto.builder()
                .id(policy.getId())
                .restaurantId(policy.getRestaurant().getId())
                .build();
    }
}
