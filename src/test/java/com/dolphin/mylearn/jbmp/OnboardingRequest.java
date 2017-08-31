package com.dolphin.mylearn.jbmp;

import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;

/**
 * Created by jichuan.wang on 2017/8/31.
 */
public class OnboardingRequest {
    public static void main(String[] args){
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration();
        cfg.setJdbcUrl("jdbc:mysql://localhost:3307/mydata?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        cfg.setJdbcUsername("root");
        cfg.setJdbcPassword("wwww");
        cfg.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        cfg.setDatabaseSchema(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP);
        ProcessEngine processEngine = cfg.buildProcessEngine();
        String pName = processEngine.getName();
        String version = ProcessEngine.VERSION;
        PrintUtil.print("ProcessEngine [" + pName + "] Version: [" + version + "]");
    }
}
