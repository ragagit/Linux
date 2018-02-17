/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.jpa.dao;

import com.raga.jpa.entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author raga
 */
@Repository
@Transactional
public class PersonDao implements IPersonDao{
    
    @PersistenceContext
    EntityManager entManager;

    @Override
    public List<Person> getAllPersons() {
        String hql = "SELECT p FROM Person p";
	return (List<Person>) entManager.createQuery(hql, Person.class).getResultList();        
    }

    @Override
    public Person getPerson(long person_id) {
        return entManager.find(Person.class, person_id);
    }

    @Override
    public void addPerson(Person person) {
        entManager.persist(person);
    }

    @Override
    public void updatePerson(Person person) {
        entManager.merge(person);
    }

    @Override
    public void deletePerson(long person_id) {
        entManager.remove(getPerson(person_id));
    }

    @Override
    public boolean personExists(String fName, String lName) {
        String hql = "SELECT p FROM Person WHERE first_name = ? and last_name = ?";
	int count = entManager.createQuery(hql).setParameter(1, fName)
		              .setParameter(2, lName).getResultList().size();
        
	return count > 0 ? true : false;
    }
    
    
}
