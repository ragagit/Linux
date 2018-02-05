/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.javaspringreview;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author raga
 */
@Component
public class MyColorManager {
    
    //Even without using any of the @Autowire this works because of the autodetect
    @Autowired
    private MyColor color;
    
    @Value("Hello from MyColorManager")
    private String message;
     
    @PostConstruct
    public void init(){
        System.out.println("MyColorManager bean init() method");
    }
    
    //Dependency Injection by Constructor
    //@Autowired
    MyColorManager( MyColor color){
        this.color = color;
    }
    
    //Dependency Injection by setter
//    @Autowired
//    public void setMyColor( MyColor color){
//        this.color = color;
//    }
    
    
    void showColor(){
        color.printColor();
    }
    
    void showMessage(){
        System.out.println(message);
    }
    
    @PreDestroy
    public void destroy(){
        System.out.println("MyColorManager bean destroy() method");
    }
    
    
}
