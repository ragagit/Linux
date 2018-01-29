/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Person.cpp
 * Author: raga
 * 
 * Created on January 26, 2018, 8:22 PM
 */

#include "Person.h"

//Person::Person() {
//}

Person::Person() {
   // this->name = Person::Builder.getEmail();
}

Person::~Person() {
}

Person::Builder::Builder(string name, string email){
    this->name = name;
    this->email = email;
}

