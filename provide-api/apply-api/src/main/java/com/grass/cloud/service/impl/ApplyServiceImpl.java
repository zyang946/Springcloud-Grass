package com.grass.cloud.service.impl;

import com.springboot.cloud.util.Response;
import com.sun.media.jfxmedia.logging.Logger;
import com.alibaba.fastjson.JSONObject;
import com.grass.cloud.entity.Apply;
import com.grass.cloud.mapper.IApplyMapper;
import com.grass.cloud.mapper.IApplyMapper;
import com.grass.cloud.service.IApplyService;
import com.grass.cloud.service.IApplyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;


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
    public Response findAllApplys(String from_id, Integer page, Integer limit, String sort, HttpHeaders headers) {
        List<Apply> applyList = iApplyMapper.findAllApplys(from_id);
        int total = applyList.size();
        // System.out.println(sort);
        if(sort.equals("-time")) {
            // Collections.reverse(applyList);
            // System.out.println(applyList);
            Collections.sort(applyList, new Comparator<Apply>() {
                @Override
                public int compare(Apply p1, Apply p2) {
                    return p2.getFrom_time().compareTo(p1.getFrom_time());
                }
            });
        } else {
            Collections.sort(applyList, new Comparator<Apply>() {
                @Override
                public int compare(Apply p1, Apply p2) {
                    return p1.getFrom_time().compareTo(p2.getFrom_time());
                }
            });
        }
        // System.out.println(applyList);
        List<Apply> subList = applyList.stream().skip((page - 1) * limit).limit(limit).collect(Collectors.toList());
        JSONObject object = new JSONObject();
        object.put("total", total);
        object.put("applyList", subList);
        return new Response<>(1, "success", object);
        // return iApplyMapper.findAllApplys();
    }

    @Override
    public Response findAllApplysTo(String to_id, Integer page, Integer limit, String sort, HttpHeaders headers) {
        List<Apply> applyList = iApplyMapper.findAllApplysTo(to_id);
        int total = applyList.size();
        // System.out.println(sort);
        if(sort.equals("-time")) {
            // Collections.reverse(applyList);
            // System.out.println(applyList);
            Collections.sort(applyList, new Comparator<Apply>() {
                @Override
                public int compare(Apply p1, Apply p2) {
                    return p2.getFrom_time().compareTo(p1.getFrom_time());
                }
            });
        } else {
            Collections.sort(applyList, new Comparator<Apply>() {
                @Override
                public int compare(Apply p1, Apply p2) {
                    return p1.getFrom_time().compareTo(p2.getFrom_time());
                }
            });
        }
        // System.out.println(applyList);
        List<Apply> subList = applyList.stream().skip((page - 1) * limit).limit(limit).collect(Collectors.toList());
        JSONObject object = new JSONObject();
        object.put("total", total);
        object.put("applyList", subList);
        return new Response<>(1, "success", object);
        // return new Response<>(1, "success", applyList);
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

    @Override
    public Response deleteApply(Integer id, HttpHeaders headers) {
        int yes = iApplyMapper.deleteApply(id);
        return new Response<>(1, "success", id);
        // return iApplyMapper.insertApply(apply);
    }
}