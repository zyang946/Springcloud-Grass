package com.springboot.cloud.Service.impl;

import com.springboot.cloud.Service.TestApiService;
import com.springboot.cloud.util.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class TestApiServiceImpl implements TestApiService {
    public Response test(Integer id, HttpHeaders headers) {
        return new Response<>(1,"sucess","nacos registry, serverPort" + "\t id" + id);
    }
}
