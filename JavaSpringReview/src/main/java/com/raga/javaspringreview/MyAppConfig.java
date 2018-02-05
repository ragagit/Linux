/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.javaspringreview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author raga
 */
@Configuration
@ComponentScan
//@PropertySource("classpath:/db.properties")
//@PropertySource("./db.properties")
public class MyAppConfig {
    
    @Autowired
    Environment env;

    @Bean(name = "myColorBean")
    public MyColor getColor() {
        return new RedColor();
    }

    @Bean(name = "dbConfig")
    public MyDbConfig getDbConfig() {

        MyDbConfig dbConf = new MyDbConfig();
        dbConf.setDbHost(env.getProperty("db.host.url"));
        dbConf.setDbPort(env.getProperty("db.port.number"));
        dbConf.setDbService(env.getProperty("db.service.name"));
        dbConf.setDbUser(env.getProperty("db.user"));
        dbConf.setDbPassword(env.getProperty("db.password"));
        return dbConf;
        
    }
}
