package com.springboot.cloud.service;

import com.springboot.cloud.dto.TableDto;
import com.springboot.cloud.utils.Response;
import org.springframework.http.HttpHeaders;

public interface CodeGenerateService {
    public Response genereateCode(TableDto tableDto, HttpHeaders header);
}
