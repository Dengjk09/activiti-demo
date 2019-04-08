package com.dengjk.activitidemo;

import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.engine.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Dengjk
 * @create 2019-02-21 22:53
 * @desc 与spring整合测试
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class AtivitiTest {



    @Autowired
    private ProcessRuntime processRuntime;


    /**
     *测试注入的对象是否成功
     */
    @Test
    public void test() {
        System.out.println(processRuntime);
    }

}
