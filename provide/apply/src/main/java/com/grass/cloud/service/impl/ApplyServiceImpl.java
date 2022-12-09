package com.grass.cloud.service.impl;

import com.springboot.cloud.util.Response;
import com.grass.cloud.entity.Apply;
import com.grass.cloud.service.IApplyService;
import com.grass.cloud.service.IApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import javax.annotation.Resource;

/**
 * 简单用户链接Mysql数据库微服务（通过@Service注解标注该类为持久化操作对象）。
 *
 * @author hmilyylimh
 *
 * @version 0.0.1
 *
 * @date 2017-10-19
 *
 */
@Service
public class ApplyServiceImpl implements IApplyService {

    @Resource
    RestTemplate restTemplate;
    private String getServiceUrl() {
        return "http://127.0.0.1:8328";
    }

    @Override
    public Response findApplyById(Integer id, HttpHeaders headers) {
        String serviceUrl = getServiceUrl();
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                serviceUrl + "/apply/" + id,
                HttpMethod.GET,
                requestEntity,
                Response.class);
        Response result = re.getBody();
        return result;
    }

    @Override
    public Response findAllApplys(int from_id, int page, int limit, String sort, HttpHeaders headers) {
        String serviceUrl = getServiceUrl();
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                serviceUrl + String.format("/apply/listFrom?from_id=%d&page=%d&limit=%d&sort=%s", from_id, page, limit, sort),
                HttpMethod.GET,
                requestEntity,
                Response.class);
        Response result = re.getBody();
        return result;
    }

    @Override
    public Response findAllApplysTo(Integer to_id, HttpHeaders headers) {
        String serviceUrl = getServiceUrl();
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                serviceUrl + "/apply/listTo?to_id=" + to_id,
                HttpMethod.GET,
                requestEntity,
                Response.class);
        Response result = re.getBody();
        return result;
    }

    @Override
    public Response insertApply(Apply apply, HttpHeaders headers) {
        String serviceUrl = getServiceUrl();
        HttpEntity requestEntity = new HttpEntity(apply, headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                serviceUrl + "/apply/addApply",
                HttpMethod.POST,
                requestEntity,
                Response.class);
        Response result = re.getBody();
        return result;
    }

    @Override
    public Response updateApply(Apply apply, HttpHeaders headers) {
        String serviceUrl = getServiceUrl();
        HttpEntity requestEntity = new HttpEntity(apply, headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                serviceUrl + "/apply/approval",
                HttpMethod.POST,
                requestEntity,
                Response.class);
        Response result = re.getBody();
        return result;
    }

    @Override
    public Response deleteApply(Apply apply, HttpHeaders headers) {
        String serviceUrl = getServiceUrl();
        HttpEntity requestEntity = new HttpEntity(apply, headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                serviceUrl + "/apply/deleteApply",
                HttpMethod.POST,
                requestEntity,
                Response.class);
        Response result = re.getBody();
        return result;
    }
}