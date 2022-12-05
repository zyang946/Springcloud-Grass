package com.springboot.cloud.Controller;

import com.springboot.cloud.Service.TestApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

import static org.springframework.http.ResponseEntity.ok;
@RestController
@Api("test-api")
@RequestMapping("/api/test")
public class TestApiController {
    @Autowired
    TestApiService testApiService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestApiController.class);
    @GetMapping(value = "/test/{id}")
    @CrossOrigin(origins = "*")
    @ApiOperation("test-api")
    public HttpEntity test(@RequestHeader HttpHeaders headers, @PathVariable("id") Integer id) {
        TestApiController.LOGGER.info("test api");
        return ok(testApiService.test(id, headers));
    }
}
