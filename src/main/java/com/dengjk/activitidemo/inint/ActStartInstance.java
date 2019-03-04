package com.dengjk.activitidemo.inint;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/**
 * @author Dengjk
 * @create 2019-02-24 16:27
 * @desc  流程实例化，发起一个流程
 **/
public class ActStartInstance {

    @Test
    public void holidayStartInstance() {
        /**获取默认processEngine对象 1.资源目录下要有activiti.cfg.xml文件 2.bean的id要是processEngineConfiguration*/
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        /**获取RuntimeService对象*/
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();

        /**通过流程key获取流程实例对象*/
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday");

        System.out.println("流程部署Id:"+processInstance.getDeploymentId());
        System.out.println("流程定义id:"+processInstance.getProcessDefinitionId());
        System.out.println("流程实例id:"+processInstance.getId());
        /**查看mysql 表中数据*/
    }

}
