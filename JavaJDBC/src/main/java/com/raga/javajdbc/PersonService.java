/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.javajdbc;

import java.util.List;

/**
 *
 * @author raga
 */
public interface PersonService {
 
    public void addPerson(Person person);
 
    public void editPerson(Person person, int personId);
 
    public void deletePerson(int personId);
 
    public Person find(int personId);
 
    public List<Person> findAll();
}