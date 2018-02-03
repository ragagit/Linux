/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   OracleDatabase.h
 * Author: raga
 *
 * Created on January 26, 2018, 2:39 PM
 */

#ifndef ORACLEDATABASE_H
#define ORACLEDATABASE_H

#include "Database.h"
#include <iostream>

using namespace std;

class OracleDatabase : public Database{
public:
    OracleDatabase();
    OracleDatabase(const OracleDatabase& orig);
    virtual ~OracleDatabase();
    void connect();
    void disconnect();
private:

};

#endif /* ORACLEDATABASE_H */

