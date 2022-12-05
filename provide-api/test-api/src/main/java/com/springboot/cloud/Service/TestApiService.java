package com.springboot.cloud.Service;


import com.springboot.cloud.util.Response;
import org.springframework.http.HttpHeaders;

public interface TestApiService {
    public Response test(Integer id, HttpHeaders headers);
}
