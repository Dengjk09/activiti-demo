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
    public void actTaskComplete() {
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
    public void actTaskCompleteByAssignee() {
        /**获取默认processEngine对象 1.资源目录下要有activiti.cfg.xml文件 2.bean的id要是processEngineConfiguration*/
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        /**获取TaskService对象*/
        TaskService taskService = defaultProcessEngine.getTaskService();

        /**流程实例的的key*/
        String definitonKey = "holiday";
        List<Task> zhangsanTask = taskService.createTaskQuery().processDefinitionKey(definitonKey).taskAssignee("zhaoliu").list();
        /**获取到某个人的所有任务并且全部执行完*/
        for (Task task : zhangsanTask) {
            taskService.complete(task.getId());
            System.out.println("任务实例:" + task.getTaskDefinitionKey() + ":任务名称" + task.getName() + ":执行人" + task.getAssignee());
        }
    }


    /**
     * 任务的拾取(就是将多个候选人转换为真正任务的处理人-让任务的assignee有值)
     * <p>
     * 当我们在bpmn文件中设置了多个处理人(候选人),我们需要把候选人变成当前处理人
     */
    public void actTaskCandidate() {
        /**首先通过候选人查询当前任务*/
        /**获取默认processEngine对象 1.资源目录下要有activiti.cfg.xml文件 2.bean的id要是processEngineConfiguration*/
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        /**获取TaskService对象*/
        TaskService taskService = defaultProcessEngine.getTaskService();
        /**流程实例的的key*/
        String definitonKey = "holiday";
        String candidate = "zhangsan";
        List<Task> zhangsanTask = taskService.createTaskQuery().processDefinitionKey(definitonKey).taskCandidateUser(candidate).list();
        /**获取到某个人的所有任务并且全部执行完*/
        for (Task task : zhangsanTask) {
            /**遍历操作把张三的候选人变成处理人  这个时候去查看act_hi_actinst表中的数据就会有当前处理人了*/
            taskService.claim(task.getId(), candidate);
            /**再进一步完成当前任务*/
            taskService.complete(task.getId());
            System.out.println("任务实例:" + task.getTaskDefinitionKey() + ":任务名称" + task.getName() + ":执行人" + task.getAssignee());


            /**===========分割============*/
            /**任务的交接 ,就是把当前任务的处理人该要处理的人*/
            taskService.setAssignee(task.getId(), "jiakang");

        }
    }
}
