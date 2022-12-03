package com.springboot.cloud.Controller;

import com.springboot.cloud.Service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.ResponseEntity.ok;

@RestController
@Api("test")
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    @GetMapping(value = "/test/{id}")
    @CrossOrigin(origins = "*")
    @ApiOperation("test")
    public HttpEntity test(@RequestHeader HttpHeaders headers, @PathVariable("id") Integer id) {
        TestController.LOGGER.info("test api");
        return ok(testService.test(id, headers));
    }
}
