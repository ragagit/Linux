/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author raga
 */
@Entity
public class Person {
    
    @Id
    @GeneratedValue
    long Person_id;
    
    String first_name;
    String last_name;
    String email;
    int    age;
    
    public Person(){
        
    }

    public Person(int person_id,  String first_name, String last_name, String email, int age) {
        this.Person_id = person_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.age = age;
    }
    
    
    public Person( String first_name, String last_name, String email, int age) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.age = age;
    }

    public long getPerson_id() {
        return Person_id;
    }

    public void setPerson_id(long Person_id) {
        this.Person_id = Person_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    
}
