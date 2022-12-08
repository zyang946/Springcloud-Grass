package com.grass.cloud.service.impl;

import com.springboot.cloud.util.Response;
import com.grass.cloud.entity.Apply;
import com.grass.cloud.mapper.IApplyMapper;
import com.grass.cloud.mapper.IApplyMapper;
import com.grass.cloud.service.IApplyService;
import com.grass.cloud.service.IApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


import java.util.List;

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

    @Autowired
    IApplyMapper iApplyMapper;

    @Override
    public Response findApplyById(Integer id, HttpHeaders headers) {
        Apply apply = iApplyMapper.findApplyById(id);
        return new Response<>(1,"success", apply);
        // return iApplyMapper.findApplyById(id);
    }

    @Override
    public Response findAllApplys(String from_id, HttpHeaders headers) {
        List<Apply> applyList = iApplyMapper.findAllApplys(from_id);
        return new Response<>(1, "success", applyList);
        // return iApplyMapper.findAllApplys();
    }

    @Override
    public Response findAllApplysTo(String to_id, HttpHeaders headers) {
        List<Apply> applyList = iApplyMapper.findAllApplysTo(to_id);
        return new Response<>(1, "success", applyList);
        // return iApplyMapper.findAllApplys();
    }

    @Override
    public Response insertApply(Apply apply, HttpHeaders headers) {
        int yes = iApplyMapper.insertApply(apply);
        return new Response<>(1, "success", apply);
        // return iApplyMapper.insertApply(apply);
    }

    @Override
    public Response updateApply(Apply apply, HttpHeaders headers) {
        int yes = iApplyMapper.updateApply(apply);
        return new Response<>(1, "success", apply);
        // return iApplyMapper.insertApply(apply);
    }
}