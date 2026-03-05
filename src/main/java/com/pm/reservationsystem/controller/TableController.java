package com.pm.reservationsystem.controller;

import com.pm.reservationsystem.dto.TableRequestDto;
import com.pm.reservationsystem.dto.TableResponseDto;
import com.pm.reservationsystem.model.Table;
import com.pm.reservationsystem.service.TableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/table")
@RequiredArgsConstructor
public class TableController {


    private final TableService tableService;


    @PostMapping
    public ResponseEntity<TableResponseDto> createTable(@Valid  @RequestBody TableRequestDto tableRequestDto) {
        TableResponseDto tableResponseDto = tableService.createTable(tableRequestDto);
        var location = UriComponentsBuilder
                .fromPath("/table/{id}")
                .buildAndExpand(tableResponseDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(tableResponseDto);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<TableResponseDto>> getRestaurantTables(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(tableService.getAllTablesByRestaurant(restaurantId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/restaurant/{restaurantId}/group")
    public ResponseEntity<List<TableResponseDto>> getRestaurantTablesByGroup(@PathVariable Long restaurantId, @RequestParam Long groupId) {
        return ResponseEntity.ok(tableService.getTablesByRestaurantAndGroup(restaurantId, groupId));
    }




}
