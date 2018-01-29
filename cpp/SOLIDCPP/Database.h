/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Database.h
 * Author: raga
 *
 * Created on January 26, 2018, 2:08 PM
 */

#ifndef DATABASE_H
#define DATABASE_H

class Database {
public:
    virtual void connect()=0;
    virtual void disconnect()=0;
};

#endif /* DATABASE_H */

