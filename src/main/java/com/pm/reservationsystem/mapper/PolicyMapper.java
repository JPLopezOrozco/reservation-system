package com.pm.reservationsystem.mapper;

import com.pm.reservationsystem.dto.PolicyRequestDto;
import com.pm.reservationsystem.dto.PolicyResponseDto;
import com.pm.reservationsystem.model.Policy;
import com.pm.reservationsystem.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class PolicyMapper {


    public PolicyResponseDto toPolicyResponseDto(Policy policy) {
        return PolicyResponseDto.builder()
                .id(policy.getId())
                .restaurantId(policy.getRestaurant().getId())
                .build();
    }

    public Policy toPolicy(PolicyRequestDto policyRequestDto, Restaurant restaurant) {
        return Policy.builder()
                .restaurant(restaurant)
                .build();
    }
}
