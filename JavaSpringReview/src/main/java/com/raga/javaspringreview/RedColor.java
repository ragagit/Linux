/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.javaspringreview;

//import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Qualifier;


/**
 *
 * @author raga
 */
@Component
@Qualifier("redcolor")
public class RedColor implements MyColor {
  
    @PostConstruct
    public void init(){
        System.out.println("Red bean init() method");
    }
    
    @Override
    public void printColor() {
        System.out.println("This is red color");
    }
    
    @PreDestroy
    public void destroy(){
        System.out.println("Red bean destroy");
    }
    
}
