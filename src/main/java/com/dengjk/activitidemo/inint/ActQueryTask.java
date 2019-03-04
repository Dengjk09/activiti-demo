package com.dengjk.activitidemo.inint;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * @author Dengjk
 * @create 2019-02-24 16:47
 * @desc
 **/
public class ActQueryTask {

    @Test
    public void actQueryTask() {
        /**获取默认processEngine对象 1.资源目录下要有activiti.cfg.xml文件 2.bean的id要是processEngineConfiguration*/
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        /**获取TaskService对象*/
        TaskService taskService = defaultProcessEngine.getTaskService();

        /**获取任务对象 获取当前人的任务列表*/
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("holiday")
                .taskAssignee("zhangsan")
                .list();
        for (Task task : taskList) {
            System.out.println("流程实例id:" + task.getProcessInstanceId());
            System.out.println("任务id:" + task.getId());
            System.out.println("任务负责人:" + task.getAssignee());
            System.out.println("任务名称:" + task.getName());
        }
    }
}
