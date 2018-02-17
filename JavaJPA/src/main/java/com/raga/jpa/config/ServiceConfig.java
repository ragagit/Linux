/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.jpa.config;

import com.raga.service.IPersonService;
import com.raga.service.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author raga
 */
@Configuration
public class ServiceConfig {
    
    @Bean
    IPersonService getPersonService(){
        return new PersonService();
    }
    
}
