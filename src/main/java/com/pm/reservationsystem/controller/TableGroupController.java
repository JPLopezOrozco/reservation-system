package com.pm.reservationsystem.controller;

import com.pm.reservationsystem.dto.TableGroupRequestDto;
import com.pm.reservationsystem.dto.TableGroupResponseDto;
import com.pm.reservationsystem.service.TableGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class TableGroupController {

    private final TableGroupService tableGroupService;

    @PostMapping
    public ResponseEntity<TableGroupResponseDto> createGroup(@Valid @RequestBody TableGroupRequestDto tableGroupRequestDto) {
        TableGroupResponseDto tableGroupResponseDto = tableGroupService.createTableGroup(tableGroupRequestDto);
        var location = UriComponentsBuilder
                .fromPath("/groups/{id}")
                .buildAndExpand(tableGroupResponseDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(tableGroupResponseDto);
    }

}
