/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Person.h
 * Author: raga
 *
 * Created on January 26, 2018, 8:22 PM
 */

#ifndef PERSON_H
#define PERSON_H

#include <string>

using namespace std;

class Person {
public:
    Person();
    Person(const Person& orig);
    virtual ~Person();
private:
    string name;
    string email;
    string address;
    int age;
    
public:
    static class Builder{
        
        private:
            string name;
            string email;
            string address;
            int age;
            string university;
            
        public:    
            Builder( string name, string email);
            void setAddress(string address);
            void setAge(int age);
            string getAddress();
            string getName();
            string getEmail();
            int    getAge();
    };

};

#endif /* PERSON_H */

