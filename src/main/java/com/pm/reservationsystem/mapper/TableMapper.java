package com.pm.reservationsystem.mapper;

import com.pm.reservationsystem.dto.TableResponseDto;
import com.pm.reservationsystem.model.Table;

public class TableMapper {

    public TableResponseDto toTableResponseDto(Table table) {
        TableResponseDto tableResponseDto = new TableResponseDto();
        tableResponseDto.setTableName(table.getTableName());
        tableResponseDto.setMinCapacity(table.getMinCapacity());
        tableResponseDto.setMaxCapacity(table.getMaxCapacity());
        tableResponseDto.setTableGroup(table.getTableGroup().toString());
        return tableResponseDto;
    }
}
