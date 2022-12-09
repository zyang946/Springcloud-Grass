package com.springboot.cloud.controller;

import com.springboot.cloud.dto.TableDto;
import com.springboot.cloud.service.CodeGenerateService;
import com.springboot.cloud.util.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/code_generate")
public class CodeGenerateController {
    @Autowired
    private CodeGenerateService codeGenerateService;
    private static final Logger logger = LoggerFactory.getLogger(CodeGenerateController.class);

    @PostMapping("/")
    public ResponseEntity<Response> GenerateCode(@RequestBody TableDto tableDto, @RequestHeader HttpHeaders headers) {
        logger.info("generate code");
        return ResponseEntity.ok(codeGenerateService.genereateCode(tableDto, headers));
    }
}
