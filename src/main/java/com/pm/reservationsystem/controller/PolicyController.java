package com.pm.reservationsystem.controller;

import com.pm.reservationsystem.dto.PolicyResponseDto;
import com.pm.reservationsystem.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/policy")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<PolicyResponseDto> getPolicyById(@PathVariable("id") Long id) {
        PolicyResponseDto policyResponseDto = policyService.getPolicyByRestaurantId(id);
        return ResponseEntity.ok(policyResponseDto);
    }



}
