package com.grass.cloud.controller;

import com.grass.cloud.entity.Apply;
import com.grass.cloud.service.IApplyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.http.HttpEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.util.List;

/**
 * 用户微服务Controller。
 *
 * @author qrn
 *
 * @version 0.0.1
 *
 * @date 2022-12-05
 *
 */
@RestController
@Api("apply")
@RequestMapping("/apply")
public class ApplyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyController.class);

    @Autowired
    private IApplyService iApplyService;

    @GetMapping("/{id}")
    // @CrossOrigin(origins = "*")
    // @ApiOperation("findApplyById")
    public HttpEntity findApplyById(@RequestHeader HttpHeaders headers, @PathVariable Integer id) {
        return ok(this.iApplyService.findApplyById(id, headers));
    }

    @GetMapping("/all")
    // @CrossOrigin(origins = "*")
    // @ApiOperation("findApplyById")
    public HttpEntity findApplyAll(
        @RequestParam(value = "page", required=false) Integer page,  
        @RequestParam(value = "limit", required=false) Integer limit,
        @RequestParam(value = "sort", required=false) String sort,
        @RequestHeader HttpHeaders headers) {
        return ok(this.iApplyService.findApplyAll(page, limit, sort, headers));
    }

    @GetMapping("/listFrom")
    // @CrossOrigin(origins = "*")
    // @ApiOperation("findApplyList")
    public HttpEntity findApplyList(@RequestParam(value = "from_id", required=false) String from_id,
    @RequestParam(value = "page", required=false) Integer page,  
    @RequestParam(value = "limit", required=false) Integer limit,
    @RequestParam(value = "sort", required=false) String sort,
    @RequestHeader HttpHeaders headers) {
        LOGGER.info("查询我的申请中。。。");
        return ok(this.iApplyService.findAllApplys(from_id, page, limit, sort, headers));
    }

    @GetMapping("/listTo")
    // @CrossOrigin(origins = "*")
    // @ApiOperation("findApplyList")
    public HttpEntity findApplyListTo(@RequestParam(value = "to_id", required=false) String to_id,  
    @RequestParam(value = "page", required=false) Integer page,  
    @RequestParam(value = "limit", required=false) Integer limit,
    @RequestParam(value = "sort", required=false) String sort,
    @RequestHeader HttpHeaders headers) {
        LOGGER.info("查询我的审批中。。。");
        return ok(this.iApplyService.findAllApplysTo(to_id, page, limit, sort, headers));
    }

    /**
     * 添加一个student,使用postMapping接收post请求
     *
     * http://localhost:8310/simple/addApply?applyname=apply11&age=11&balance=11
     *
     * @return
     */
    @PostMapping("/addApply")
    // @CrossOrigin(origins = "*")
    // @ApiOperation("addApply")
    public HttpEntity addApply(@RequestHeader HttpHeaders headers, @RequestBody Apply apply){
        LOGGER.info("提交申请中。。。");
        return ok(iApplyService.insertApply(apply, headers));
    }

    @PostMapping("/approval")
    // @CrossOrigin(origins = "*")
    // @ApiOperation("addApply")
    public HttpEntity approval(@RequestHeader HttpHeaders headers, @RequestBody Apply approval){
        LOGGER.info("审批申请中。。。");
        System.out.println(approval);
        return ok(iApplyService.updateApply(approval, headers));
    }

    @PostMapping("/deleteApply")
    // @CrossOrigin(origins = "*")
    // @ApiOperation("addApply")
    public HttpEntity delete(@RequestHeader HttpHeaders headers, @RequestBody Apply apply){
        LOGGER.info("删除申请中。。。");
        Integer id = apply.getId();
        return ok(iApplyService.deleteApply(id, headers));
    }
}
