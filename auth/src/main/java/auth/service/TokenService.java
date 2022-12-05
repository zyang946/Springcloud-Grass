package auth.service;

import com.springboot.cloud.util.Response;

import auth.dto.BasicAuthDto;
import org.springframework.http.HttpHeaders;

public interface TokenService {
    Response getToken(BasicAuthDto dto, HttpHeaders headers);
}
