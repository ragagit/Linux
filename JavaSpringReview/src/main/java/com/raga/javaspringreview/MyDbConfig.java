/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.javaspringreview;

/**
 *
 * @author raga
 */
public class MyDbConfig {
 
    private String dbHost;
    private String dbPort;
    private String dbService;
    private String dbUrl;
    private String dbPassword;
    private String dbUser;
     
    public String getDbHost() {
        return dbHost;
    }
    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }
    public String getDbPort() {
        return dbPort;
    }
    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }
    public String getDbService() {
        return dbService;
    }
    public void setDbService(String dbService) {
        this.dbService = dbService;
    }
    public String getDbUrl() {
        return dbUrl;
    }
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }
    public String getDbPassword() {
        return dbPassword;
    }
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
    public void setDbUser(String dbUser){
        this.dbUser = dbUser;
    }
     
    @Override
    public String toString(){
        return dbHost+"|"+dbPort+"|"+dbService+"|"+dbUrl+"|"+dbPassword;
    }
}
