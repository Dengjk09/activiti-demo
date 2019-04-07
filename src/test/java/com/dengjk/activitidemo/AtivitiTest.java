package com.dengjk.activitidemo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Dengjk
 * @create 2019-02-21 22:53
 * @desc 与spring整合测试
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/activiti-spring.xml")
public class AtivitiTest {


    @Autowired
    private RepositoryService repositoryService;


    /**
     *测试注入的对象是否成功
     */
    @Test
    public void test() {
        System.out.println(repositoryService);
    }

}
