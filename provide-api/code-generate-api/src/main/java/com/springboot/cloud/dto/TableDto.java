package com.springboot.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableDto {
    String tableName;
    List<ColumnDto> columnDtoList;
}
