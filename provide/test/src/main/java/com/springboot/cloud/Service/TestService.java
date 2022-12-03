package com.springboot.cloud.Service;


import com.springboot.cloud.util.Response;
import org.springframework.http.HttpHeaders;

public interface TestService {
    public Response test(Integer id, HttpHeaders headers);
}
