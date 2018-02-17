/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.service;

import com.raga.jpa.entity.Person;
import java.util.List;

/**
 *
 * @author raga
 */
public interface IPersonService {
    List<Person> getAllPersons();
    Person getPersonById(long person_id);
    boolean addPerson(Person person);
    void updatePerson(Person person);
    void deletePerson(long person_id);
}
