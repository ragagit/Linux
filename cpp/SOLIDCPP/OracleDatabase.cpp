/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   OracleDatabase.cpp
 * Author: raga
 * 
 * Created on January 26, 2018, 2:39 PM
 */

#include "OracleDatabase.h"


OracleDatabase::OracleDatabase() {
}

OracleDatabase::OracleDatabase(const OracleDatabase& orig) {
}

OracleDatabase::~OracleDatabase() {
}

void OracleDatabase::connect(){
    cout << "Connecting to OracleDatabase ..." << endl;
}

void OracleDatabase::disconnect(){
    cout << "Disconnecting from OracleDatabase ..." << endl;
}

