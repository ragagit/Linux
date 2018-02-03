/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   DatabaseHandler.h
 * Author: raga
 *
 * Created on January 26, 2018, 3:10 PM
 */

#ifndef DATABASEHANDLER_H
#define DATABASEHANDLER_H

#include "Database.h"

class DatabaseHandler {
    
public:
    DatabaseHandler();
    DatabaseHandler(Database *db);
    virtual ~DatabaseHandler();
    void connect();
    void disconnect();
private:
    Database *db;
};

#endif /* DATABASEHANDLER_H */

