package com.pm.reservationsystem.mapper;

import com.pm.reservationsystem.dto.TableRequestDto;
import com.pm.reservationsystem.dto.TableResponseDto;
import com.pm.reservationsystem.model.Restaurant;
import com.pm.reservationsystem.model.Table;
import com.pm.reservationsystem.model.TableGroup;
import org.springframework.stereotype.Component;

@Component
public class TableMapper {

    public TableResponseDto toTableResponseDto(Table table) {
        Long groupId = (table.getTableGroup() != null) ? table.getTableGroup().getId() : null;

        return TableResponseDto.builder()
                .id(table.getId())
                .restaurantId(table.getRestaurant().getId())
                .tableName(table.getTableName())
                .minCapacity(table.getMinCapacity())
                .maxCapacity(table.getMaxCapacity())
                .tableGroupId(groupId)
                .build();
    }

    public Table toTable(TableRequestDto tableRequestDto, Restaurant restaurant, TableGroup tableGroup) {
        return Table.builder()
                .restaurant(restaurant)
                .tableName(tableRequestDto.getTableName())
                .minCapacity(tableRequestDto.getMinCapacity())
                .maxCapacity(tableRequestDto.getMaxCapacity())
                .tableGroup(tableGroup)
                .build();
    }
}
