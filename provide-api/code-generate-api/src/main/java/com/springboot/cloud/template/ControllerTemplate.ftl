package com.generate.code.controller;

import com.generate.code.entity.${name};
import com.generate.code.service.${name}Service;
import com.generate.code.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${name}")
public class ${name}Controller {
    @Autowired
    private ${name}Service ${name}ServiceIntance;
    private static final Logger logger = LoggerFactory.getLogger(${name}Controller.class);
    @GetMapping ("/findAll")
    public ResponseEntity<Response> findAll(@RequestHeader HttpHeaders headers) {
            logger.info("find all ${name}");
        return ResponseEntity.ok(${name}ServiceIntance.getAll${name}s(headers));
    }

    @PostMapping("/createAndupdate")
    public ResponseEntity<Response> createAndupdate(@RequestBody ${name} ${name}dto, @RequestHeader HttpHeaders headers) {
        logger.info("create or update new ${name}");
        return ResponseEntity.ok(${name}ServiceIntance.createAndupdate(${name}dto, headers));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> delete(@RequestBody ${name} ${name}dto, @RequestHeader HttpHeaders headers) {
        logger.info("delete ${name}");
        return ResponseEntity.ok(${name}ServiceIntance.delete(${name}dto, headers));
    }
    <#list columnList as column>
    @GetMapping("/find/{${column.columnName}}")
    public ResponseEntity<Response> findBy${column.columnName}(@PathVariable ${column.type} ${column.columnName}, @RequestHeader HttpHeaders headers) {
        logger.info("find ${name} by ${column.columnName}");
         return ResponseEntity.ok(${name}ServiceIntance.findBy${column.columnName}(${column.columnName}, headers));
    }
     @DeleteMapping("/delete/{${column.columnName}}")
     public ResponseEntity<Response> deleteBy${column.columnName}(@PathVariable ${column.type} ${column.columnName}, @RequestHeader HttpHeaders headers) {
         logger.info("find ${name} by ${column.columnName}");
         return ResponseEntity.ok(${name}ServiceIntance.deletBy${column.columnName}(${column.columnName}, headers));
     }
     </#list>
}