/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.service;

import com.raga.jpa.dao.IPersonDao;
import com.raga.jpa.entity.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 *
 * @author raga
 */
@Service
public class PersonService implements IPersonService{

    @Autowired
    private IPersonDao personDao;
        
    @Override
    public List<Person> getAllPersons() {
        return personDao.getAllPersons();
    }

    @Override
    public Person getPersonById(long person_id) {
        return personDao.getPerson(person_id);
    }

    @Override
    public boolean addPerson(Person person) {
        personDao.addPerson(person);
        return true;
    }

    @Override
    public void updatePerson(Person person) {
        personDao.updatePerson(person);
    }

    @Override
    public void deletePerson(long person_id) {
        personDao.deletePerson(person_id);
    }
    
}
