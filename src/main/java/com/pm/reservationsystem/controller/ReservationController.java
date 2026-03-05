package com.pm.reservationsystem.controller;

import com.pm.reservationsystem.dto.ReservationRequestDto;
import com.pm.reservationsystem.dto.ReservationResponseDto;
import com.pm.reservationsystem.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;


    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(@Valid @RequestBody ReservationRequestDto reservationRequestDto) {
        ReservationResponseDto reservationResponseDto = reservationService.createReservation(reservationRequestDto);
        var location = UriComponentsBuilder
                .fromPath("/reservations/{id}")
                .buildAndExpand(reservationResponseDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(reservationResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> getReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.findReservationById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.noContent().build();
    }



}
