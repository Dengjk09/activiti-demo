package com.dengjk.activitidemo.query;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dengjk
 * @create 2019-03-10 18:53
 * @desc 动态的修改流程定义的处理人,之前我们都是bpmn文件中写死处理人, 我们可以在bpmn中不写死
 *      可以使用表达式UEL, 我们把bpmn中字段assignee的值写为${firstName}
 *      我们可以在程序中设置值到firstName中去
 **/
public class updateAssignee {


    public void setAssignee(){
        /**获取默认processEngine对象 1.资源目录下要有activiti.cfg.xml文件 2.bean的id要是processEngineConfiguration*/
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        /**获取RuntimeService对象*/
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();

        Map<String,Object> assigneeFild =new HashMap<>();
        assigneeFild.put("firstName","zhangsan");
        assigneeFild.put("secondName","lisi");

        /**通过流程key获取流程实例对象*/
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday",assigneeFild);
    }

}
