/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.jpa.dao;

import com.raga.jpa.entity.Person;
import java.util.List;

/**
 *
 * @author raga
 */
public interface IPersonDao {
    
    List<Person> getAllPersons();
    Person getPerson(long person_id);
    void addPerson(Person person);
    void updatePerson(Person person);
    void deletePerson(long person_id);
    boolean personExists(String fName, String lName);
    
}
