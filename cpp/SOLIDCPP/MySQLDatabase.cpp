/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   MySQLDatabase.cpp
 * Author: raga
 * 
 * Created on January 26, 2018, 2:31 PM
 */

#include "MySQLDatabase.h"


MySQLDatabase::MySQLDatabase() {
}

MySQLDatabase::MySQLDatabase(const MySQLDatabase& orig) {
}

MySQLDatabase::~MySQLDatabase() {
}

void MySQLDatabase::connect(){
    cout << "Connecting to MySQLDatabase" << endl;
}

void MySQLDatabase::disconnect(){
    cout << "Disconnecting from MySQLdatabase" << endl;
}
