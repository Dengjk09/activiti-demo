package com.dengjk.activitidemo.inint;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.junit.Test;

/**
 * @author Dengjk
 * @create 2019-02-21 23:04
 * @desc
 **/
public class CreateActTables {
    @Test
    public void createActTables() {

        /**
         * 方式1 指定配置文件,手动build
         */
        //1.创建ProcessEngineConfiguration对象  第一个参数:配置文件名称  第二个参数是processEngineConfiguration的bean的id  默认就是:processEngineConfiguration
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml","processEngineConfiguration");
        //2.创建ProcesEngine对象
        ProcessEngine processEngine = configuration.buildProcessEngine();
        //3.输出processEngine对象
        RuntimeService runtimeService = processEngine.getRuntimeService();
        System.out.println(processEngine);

        /**方式二 全部采用默认的生成
         * 条件1: 配置文件下有activiti.cfg.xml
         * 条件2: 配置文件中的bean的id必须是：processEngineConfiguration
         */

        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        System.err.println(defaultProcessEngine);

    }
}
