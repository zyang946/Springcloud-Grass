package com.generate.code.repository;

import com.generate.code.entity.${name};
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ${name}Repository  extends CrudRepository<${name}, String> {
    List<${name}> findAll();
    void delete(${name} ${name}dto);
    <#list columnList as column>
    List<${name}> findBy${column.columnName}(${column.type} ${column.columnName});
    void deleteBy${column.columnName}(${column.type} ${column.columnName});
    </#list>
}