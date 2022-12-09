package com.grass.cloud.service;


import com.springboot.cloud.util.Response;
import com.grass.cloud.entity.Apply;

import java.util.List;

import org.springframework.http.HttpHeaders;



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
public interface IApplyService {

    Response findApplyById(Integer id, HttpHeaders headers);

    Response findAllApplys(int from_id, int page, int limit, String sort, HttpHeaders headers);
    Response findAllApplysTo(Integer to_id, int page, int limit, String sort, HttpHeaders headers);

    Response insertApply(Apply apply, HttpHeaders headers);
    Response updateApply(Apply apply, HttpHeaders headers);
    Response deleteApply(Apply apply, HttpHeaders headers);

}
