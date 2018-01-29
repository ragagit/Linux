/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   MySQLDatabase.h
 * Author: raga
 *
 * Created on January 26, 2018, 2:31 PM
 */

#ifndef MYSQLDATABASE_H
#define MYSQLDATABASE_H

#include "Database.h"
#include <iostream>

using namespace std;

class MySQLDatabase: public Database {
public:
    MySQLDatabase();
    MySQLDatabase(const MySQLDatabase& orig);
    virtual ~MySQLDatabase();
    void connect();
    void disconnect();
private:

};

#endif /* MYSQLDATABASE_H */

