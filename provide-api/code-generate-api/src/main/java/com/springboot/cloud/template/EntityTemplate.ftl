package com.generate.code.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@GenericGenerator(name = "jpa-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "${tableName}")
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ${name}{
    @Id
    @Column(length = 36)
    private ${type} ${columnName};

    <#list columnList as column>
    private ${column.type} ${column.columnName};
    </#list>


    }