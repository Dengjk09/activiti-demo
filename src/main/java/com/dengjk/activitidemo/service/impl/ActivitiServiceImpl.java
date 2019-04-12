package com.dengjk.activitidemo.service.impl;

import com.dengjk.activitidemo.config.SecurityUtil;
import com.dengjk.activitidemo.service.ActivitiService;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.ClaimTaskPayloadBuilder;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dengjk
 * @create 2019-04-08 21:14
 * @desc activiti与springboot整合 ,不需要流程部署了,我们只要把bpmn文件放在processes目录下 就可以实现自动部署
 **/
@Service
public class ActivitiServiceImpl implements ActivitiService {


    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private TaskRuntime taskRuntime;


    @Autowired
    private SecurityUtil securityUtil;

    /**
     * 查询流程定义信息
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Object findProcessDefinition(Integer pageNo, Integer pageSize) {
        /**处理登入人*/
        securityUtil.logInAs("ryandawsonuk");
        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(pageNo, pageSize));
        return processDefinitionPage;
    }


    /**
     * 任务的查看和拾取
     *
     * @param name
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Object findTaskInfo(String name, Integer pageNo, Integer pageSize) {
        Page<Task> tasks = taskRuntime.tasks(Pageable.of(pageNo, pageSize));

        if (tasks != null && tasks.getTotalItems() > 0) {
            /**有内容*/
            List<Task> content = tasks.getContent();
            for (Task task : content) {
                /**先任务拾取*/
                taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());

                /**任务的完成*/
                taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(task.getId()).build());

            }
        }
        return null;
    }
}






























