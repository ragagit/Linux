/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.javaspringreview;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author raga
 */
@Component
@Qualifier("bluecolor")
public class BlueColor implements MyColor {
  
    @PostConstruct
    public void init(){
        System.out.println("Blue bean init() method");
    }
    
    @Override
    public void printColor() {
        System.out.println("This is blue color");
    }
    
    @PreDestroy
    public void destroy(){
        System.out.println("Blue bean destroy");
    }
    
}
