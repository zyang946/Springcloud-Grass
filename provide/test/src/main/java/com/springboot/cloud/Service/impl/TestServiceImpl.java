package com.springboot.cloud.Service.impl;

import com.springboot.cloud.Service.TestService;
import com.springboot.cloud.util.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {
    @Resource
    RestTemplate restTemplate;
    private String getServiceUrl(String serviceName) {
        return "http://" + serviceName;
    }
    public Response test(Integer id, HttpHeaders headers) {
        String serviceUrl = getServiceUrl("test-api");
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                serviceUrl + "/api/test/test/" + id,
                HttpMethod.GET,
                requestEntity,
                Response.class);
        Response result = re.getBody();
        return result;
    }
}
