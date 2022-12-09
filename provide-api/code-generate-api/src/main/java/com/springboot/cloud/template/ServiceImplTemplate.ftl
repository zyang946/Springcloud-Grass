package com.generate.code.service.impl;

import com.generate.code.entity.${name};
import com.generate.code.repository.${name}Repository;
import com.generate.code.service.${name}Service;
import com.generate.code.util.Response;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${name}ServiceImpl implements ${name}Service {
    @Autowired
    public ${name}Repository ${name}RepositoryInstance;

    @Override
    public Response getAll${name}s(HttpHeaders headers) {
        List<${name}> ${name}List = ${name}RepositoryInstance.findAll();
        if (${name}List.size() != 0)
            return new Response<>(1, "success", ${name}List);
        return new Response<>(0, "empty", null);
    }

    @Override
    public Response createAndupdate(${name} ${name}Dto, HttpHeaders headers) {
        ${name}RepositoryInstance.save(${name}Dto);
        return new Response<>(1, "success", ${name}Dto);
    }


    @Override
    public Response delete(${name} ${name}Dto, HttpHeaders headers) {
        ${name}RepositoryInstance.delete(${name}Dto);
        return new Response<>(1, "success", ${name}Dto);
    }
    <#list columnList as column>
    @Override
    public Response findBy${column.columnName}(${column.type} ${column.columnName}, HttpHeaders headers) {
        List<${name}> ${name}s = ${name}RepositoryInstance.findBy${column.columnName}(${column.columnName});
        if (${name}s.size() != 0)
            return new Response<>(1, "success", ${name}s);
        return new Response<>(0, "empty", ${column.columnName});
    }
    @Override
    public Response deletBy${column.columnName}(${column.type} ${column.columnName}, HttpHeaders headers) {
        ${name}RepositoryInstance.deleteBy${column.columnName}(${column.columnName});
        return new Response<>(1, "success", ${column.columnName});
    }
    </#list>
}