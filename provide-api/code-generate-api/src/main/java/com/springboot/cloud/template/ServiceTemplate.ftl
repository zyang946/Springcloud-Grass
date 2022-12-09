package com.generate.code.service;

import com.generate.code.entity.${name};
import com.generate.code.util.Response;
import org.springframework.http.HttpHeaders;

public interface ${name}Service {
    public Response getAll${name}s(HttpHeaders headers);
    public Response createAndupdate(${name} ${name}dto, HttpHeaders headers);
    public Response delete(${name} ${name}dto, HttpHeaders headers);
    <#list columnList as column>
    public Response findBy${column.columnName}(${column.type} ${column.columnName}, HttpHeaders headers);
    public Response deletBy${column.columnName}(${column.type} ${column.columnName}, HttpHeaders headers);
    </#list>
}
