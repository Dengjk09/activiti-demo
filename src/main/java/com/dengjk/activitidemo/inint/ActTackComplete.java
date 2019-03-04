package com.dengjk.activitidemo.inint;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.junit.Test;

/**
 * @author Dengjk
 * @create 2019-02-24 17:16
 * @desc 当前任务处理
 **/
public class ActTackComplete {

    @Test
    public void actTaskComplete(){
        /**获取默认processEngine对象 1.资源目录下要有activiti.cfg.xml文件 2.bean的id要是processEngineConfiguration*/
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        /**获取TaskService对象*/
        TaskService taskService = defaultProcessEngine.getTaskService();
        /**完成当前任务,任务将流转到部门经理审批*/
        taskService.complete("22505");
    }
}
