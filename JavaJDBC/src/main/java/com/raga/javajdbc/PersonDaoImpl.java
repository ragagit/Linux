/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.javajdbc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author raga
 */
@Repository
@Qualifier("personDao")
public class PersonDaoImpl implements PersonDao {
 
    @Autowired
    JdbcTemplate jdbcTemplate;
 
    public void addPerson(Person person) {
        jdbcTemplate.update("INSERT INTO person (person_id, first_name, Last_name, age) VALUES (?, ?, ?, ?)",
            person.getPersonId(), person.getFirstName(), person.getLastName(), person.getAge());
        System.out.println("Person Added!!");
    }
 
    public void editPerson(Person person, int personId) {
        jdbcTemplate.update("UPDATE person SET first_name = ? , last_name = ? , age = ? WHERE person_id = ? ",
            person.getFirstName(), person.getLastName(), person.getAge(), personId);
        System.out.println("Person Updated!!");
    }
 
    public void deletePerson(int personId) {
        jdbcTemplate.update("DELETE from person WHERE person_id = ? ", personId);
        System.out.println("Person Deleted!!");
    }
 
    public Person find(int personId) {
        Person person = (Person) jdbcTemplate.queryForObject("SELECT * FROM person where person_id = ? ",
            new Object[] { personId }, new BeanPropertyRowMapper(Person.class));
 
        return person;
    }
 
    public List < Person > findAll() {
        List < Person > persons = jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper(Person.class));
        return persons;
    }
}
