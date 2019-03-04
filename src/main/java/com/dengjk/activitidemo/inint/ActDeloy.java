package com.dengjk.activitidemo.inint;

import lombok.SneakyThrows;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * @author Dengjk
 * @create 2019-02-23 17:19
 * @desc 流程部署(加载bpmn文件, 获取流程信息入库)
 **/
public class ActDeloy {


    /**
     * 请假流程部署
     */
    @Test
    public void holidayDeloy() {

        /**获取默认processEngine对象 1.资源目录下要有activiti.cfg.xml文件 2.bean的id要是processEngineConfiguration*/
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        /**获取processRespository对象*/
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        /**获取部署对象*/
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpm/qingjia.bpmn")
                .addClasspathResource("bpm/qingjia.png")
                .name("部门请假流程")
                .deploy();

        /**输出流程名称和流程id*/
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());

        /**查看mysql 表中数据*/
    }

    /**
     * 通过读取zip包来实现流程部署
     */
    @Test
    @SneakyThrows
    public  void holidayZipDeloy(){
        /**获取默认processEngine对象 1.资源目录下要有activiti.cfg.xml文件 2.bean的id要是processEngineConfiguration*/
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        /**获取processRespository对象*/
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        /**获取文件目录下的zip的流*/
        InputStream inputStream = new ClassPathResource("/qingjia.zip").getInputStream();
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        /**获取部署对象*/
        Deployment deployment = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .name("部门请假流程")
                .deploy();

        /**输出流程名称和流程id*/
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
    }
}
