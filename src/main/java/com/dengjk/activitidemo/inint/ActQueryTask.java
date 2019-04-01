package com.dengjk.activitidemo.inint;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
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

        /**获取正在执行的任务对象*/
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();


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

        /**获取单条任务获取businessKey*/
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holiday")
                .taskAssignee("zhangsan")
                .singleResult();
        /**获取流程实例对象,得到任务id*/
        String processInstanceId = task.getProcessInstanceId();

        /**获取processInstance对象*/
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

        /**获取到businessKey了对可以查询额外的提交的信息了*/
        String businessKey = processInstance.getBusinessKey();
    }
}
