package com.springboot.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnDto {
    String type;
    String columnName;
    Boolean isKey;
    Boolean isQuery;
}
