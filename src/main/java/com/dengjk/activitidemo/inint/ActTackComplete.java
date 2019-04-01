package com.dengjk.activitidemo.inint;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

import java.util.List;

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


    /**
     * 查询当前人要执行的任务,并且执行完
     */
    @Test
    public void actTaskCompleteByAssignee(){
        /**获取默认processEngine对象 1.资源目录下要有activiti.cfg.xml文件 2.bean的id要是processEngineConfiguration*/
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        /**获取TaskService对象*/
        TaskService taskService = defaultProcessEngine.getTaskService();

        /**流程实例的的key*/
        String definitonKey ="holiday";
        List<Task> zhangsanTask = taskService.createTaskQuery().processDefinitionKey(definitonKey).taskAssignee("zhaoliu").list();
        /**获取到某个人的所有任务并且全部执行完*/
        for (Task task : zhangsanTask) {
            taskService.complete(task.getId());
            System.out.println("任务实例:"+task.getTaskDefinitionKey()+":任务名称"+task.getName()+":执行人"+task.getAssignee());
        }
    }
}
