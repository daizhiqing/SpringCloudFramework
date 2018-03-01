package com.dzq.example.controller;

import com.dzq.example.mapper.VersionMapper;
import com.dzq.example.model.ExamlpleReqModel;
import com.dzq.example.model.resp.ErrorEnum;
import com.dzq.example.model.resp.JsonView;
import com.dzq.example.service.VersionService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Project : SpringCloudFramework
 * @Package Name : com.dzq.example.controller
 * @Description : TODO
 * @Author : daizhiqing@xiaochong.com
 * @Creation Date : 2018年02月28日下午3:29
 * @ModificationHistory Who When What
 */
@RestController
public class ExampleController {
    private final static Logger logger = LoggerFactory.getLogger(ExampleController.class);

    @Autowired
    VersionService versionService;

    @ResponseBody
    @ApiOperation(value = "测试", notes = "ceshi ")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public JsonView uploadCallList(@Valid @ModelAttribute ExamlpleReqModel examlpleReqModel, BindingResult bindingResult){
        String hello = "Hello World!!!";

        if(bindingResult.hasErrors()) {
            String error = bindingResult.getFieldError().getDefaultMessage();
            logger.error(error);
            return JsonView.failed(error);
        }
        logger.info(hello+versionService.findVersionById(examlpleReqModel.getId()));
        return JsonView.success(versionService.findVersionById(examlpleReqModel.getId()));
    }
}
