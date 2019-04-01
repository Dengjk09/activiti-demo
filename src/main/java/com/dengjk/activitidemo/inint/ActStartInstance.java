package com.dengjk.activitidemo.inint;

import com.dengjk.activitidemo.holiday.Holiday;
import com.google.common.collect.Maps;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.Map;

/**
 * @author Dengjk
 * @create 2019-02-24 16:27
 * @desc  流程实例化，发起一个流程
 **/
public class ActStartInstance {

    /**
     * 流程定义实例化启动,
     */
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


    /**
     * 流程定义实例启动-设置参数参数
     *
     * 设置参数的几个节点
     * 1.在启动一个流程实例的时候《
     * 2.也可以在执行任务的时候设置taskService.complete(task.getId(),参数map);
     * 3.RunTimeService 来设置 runtimeService.setVariable();//三个参数,第一个是流程实例的id,第二个是,流程定义名称holiday,第三个是要设置的值或者变量
     *
     *
     */
    @Test
    public void holidayStartHasParam(){
        /**获取默认processEngine对象 1.资源目录下要有activiti.cfg.xml文件 2.bean的id要是processEngineConfiguration*/
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        /**获取RuntimeService对象*/
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        /**通过流程key获取流程实例对象*/
        Holiday holiday = new Holiday();
        holiday.setNum(1F);
        holiday.setHolidayName("部门请假流程-流程变量");
        Map<String,Object> map = Maps.newHashMap();
        /**在bpmn中逻辑判断的时候,需要需要定义的 holiday.num*/
        map.put("holiday",holiday);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday",map);
        System.out.println("流程部署Id:"+processInstance.getDeploymentId());
        System.out.println("流程定义id:"+processInstance.getProcessDefinitionId());
        System.out.println("流程实例id:"+processInstance.getId());
    }

}