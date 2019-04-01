package com.dengjk.activitidemo.inint;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;

/**
 * @author Dengjk
 * @create 2019-03-06 22:50
 * @desc 流程起挂和激活
 **/
public class SuspendProcessInstance {


    /**
     * 流程定义起挂和激活
     */
    @Test
    public void suspendProcess() {
        /**获取默认processEngine对象 1.资源目录下要有activiti.cfg.xml文件 2.bean的id要是processEngineConfiguration*/
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        /**获取repository*/
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        /**
         * 获取流程定义对象
         */
        ProcessDefinition holiday = repositoryService.createProcessDefinitionQuery().processDefinitionKey("holiday").singleResult();

        /**流程定义是否起挂*/
        boolean suspended = holiday.isSuspended();

        if (suspended) {
            /**如果起挂,就可以去激活*/
            repositoryService.activateProcessDefinitionById(holiday.getId(), true, null);
            System.out.println("流程定义初始状态是起挂,本次运行已经改为激活");
        } else {
            /**如果是激活,把流程定义起挂*/
            repositoryService.suspendProcessDefinitionById(holiday.getId(), true, null);
            System.out.println("流程定义初始状态是激活,本次运行已经改为起挂");
        }
    }

    @Test
    public void  susperdProcessSingle(){

    }


}
