package com.dengjk.activitidemo.query;

import lombok.SneakyThrows;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Dengjk
 * @create 2019-02-28 23:27
 * @desc 查询流程信息
 **/
public class QueryProcessDefinish {


    /**
     * 查询所有部署的流程
     */
    @Test
    public void queryProcess() {
        /**获取默认processEngine对象 1.资源目录下要有activiti.cfg.xml文件 2.bean的id要是processEngineConfiguration*/
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        /**获取流程存储对象*/
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();

        /**创建查询对象*/
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

        /**根据流程号查询出所有的流程,并根据版本号排序*/
        List<ProcessDefinition> holiday = processDefinitionQuery.processDefinitionKey("holiday").orderByProcessDefinitionId().desc().list();

        for (ProcessDefinition processDefinition : holiday) {
            System.out.println("流程Id:" + processDefinition.getDeploymentId());
            System.out.println("流程定义名称:" + processDefinition.getName());
            System.out.println("流程key:" + processDefinition.getKey());
        }
    }


    /**
     * 获取流程中的资源文件 表act_ge_bytearray的  bpmn文件以及png文件
     */
    @Test
    @SneakyThrows
    public void queryProcessFile() {
        /**获取默认processEngine对象 1.资源目录下要有activiti.cfg.xml文件 2.bean的id要是processEngineConfiguration*/
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        /**获取流程存储对象*/
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();

        /**查询对象**/
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

        ProcessDefinitionQuery holiday = processDefinitionQuery.deploymentId("holiday");

        ProcessDefinition processDefinition = holiday.singleResult();

        String deploymentId = processDefinition.getDeploymentId();

        InputStream bpmnIn = repositoryService.getResourceAsStream(deploymentId, processDefinition.getResourceName());

        InputStream pngIn = repositoryService.getResourceAsStream(deploymentId, processDefinition.getDiagramResourceName());

        /**开始输出*/

        OutputStream bpmnOut = new FileOutputStream("C:/" + processDefinition.getResourceName());
        OutputStream pngOut = new FileOutputStream("C:/" + processDefinition.getDiagramResourceName());

        /**使用工具输入*/
        IOUtils.copy(bpmnIn, bpmnOut);
        IOUtils.copy(pngIn, pngOut);

        /**关流*/
        bpmnOut.close();
        pngOut.close();
        bpmnIn.close();
        pngIn.close();

    }


    /**
     * 查询历史流程记录
     */
    @Test
    public void queryHistory() {
        /**获取默认processEngine对象 1.资源目录下要有activiti.cfg.xml文件 2.bean的id要是processEngineConfiguration*/
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        /**获取历史记录对象*/
        HistoryService historyService = defaultProcessEngine.getHistoryService();
        /**获取历史查询对象*/
        HistoricActivityInstanceQuery historicActivityInstanceQuery = historyService.createHistoricActivityInstanceQuery();

        historicActivityInstanceQuery.processInstanceId("2501");

        /**获取当前流程的历史操作记录*/
        List<HistoricActivityInstance> list = historicActivityInstanceQuery.orderByHistoricActivityInstanceStartTime().asc().list();

        for (HistoricActivityInstance historicActivityInstance : list) {
            System.out.println(historicActivityInstance.getActivityId());
            System.out.println(historicActivityInstance.getActivityName());
            System.out.println(historicActivityInstance.getProcessDefinitionId());
            System.out.println(historicActivityInstance.getProcessInstanceId());
            System.out.println(historicActivityInstance.getAssignee());
            System.out.println(historicActivityInstance.getTaskId());
        }
    }


    /**
     * 删除流程部署
     * <p>
     * 备注： 删除有一种特殊的情况
     * 1.如果执行删除流程,这个时候如果,被删除的流程还有未审批结束的任务,这个时候删除,会执行失败,
     * 解决办法：deleteDeployment("1",true),执行删除时候多加一个参数,表示级联删除,它会先删除目前该流程下所有未审批完成的任务,再删除流程
     * 2.如果被删除的流程没有未执行的完成的任务,可直接删除
     */
    @Test
    public void deleteProcess() {
        /**获取默认processEngine对象 1.资源目录下要有activiti.cfg.xml文件 2.bean的id要是processEngineConfiguration*/
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        /**获取流程存储对象*/
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        /**执行删除*/
        repositoryService.deleteDeployment("1");
        // repositoryService.deleteDeployment("1",true);
    }
}
