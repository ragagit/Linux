/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.jpa.controller;

import com.raga.jpa.entity.Person;
import com.raga.service.IPersonService;
import com.raga.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author raga
 */
//@RestController
@Controller
//@RequestMapping("person")
public class PersonController {
    
    @Autowired
    private IPersonService personService;
    
    @RequestMapping(value="/")
    public String index(){
        return "index";
    }
    
    @RequestMapping(value="person/showPerson")
    public String showPerson(@RequestParam("id") long id, Model model){
        
        Person person = personService.getPersonById(id);
        model.addAttribute("fName", person.getFirst_name());
        model.addAttribute("lName", person.getLast_name());
        model.addAttribute("email", person.getEmail());
        model.addAttribute("age", person.getAge());
        
        return "showPerson";
    }
    
    @RequestMapping(value="person/{id}", method=RequestMethod.GET )
    public Person showPerson1(@PathVariable("id") long id){
       
        Person person = personService.getPersonById(id);
        
            return person;
        
    }

    @RequestMapping(value="person", method=RequestMethod.GET )
    public Person showPerson2(@RequestParam("id") long id){
       
        Person person = personService.getPersonById(id);
        
            return person;
        
    }
    
//    @RequestMapping(value="person/{id}", method=RequestMethod.GET )
//    public String showPerson(@PathVariable("id") long id){
//        return "Value id=" + id;
//    }
    
    @RequestMapping(value="person/show", method=RequestMethod.GET)
    public String showWelcome(){
        return "Welcome to Person Service";
    }
    
}
