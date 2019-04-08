package com.dengjk.activitidemo.controller;

import com.dengjk.activitidemo.service.ActivitiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dengjk
 * @create 2019-04-08 21:08
 * @desc
 **/

@RestController
@RequestMapping("/activiti")
@Api(value = "查询定义信息")
public class ActivitiController {


    @Autowired
    private ActivitiService activitiService;


    @GetMapping("findProcessDefinition")
    @ApiOperation("查询流程定义信息")
    public Object findProcessDefinition(@RequestParam(defaultValue = "0") Integer pageNo, Integer pageSize) {
       return activitiService.findProcessDefinition(pageNo,pageSize);
    }
}
