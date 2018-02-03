/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   DatabaseHandler.cpp
 * Author: raga
 * 
 * Created on January 26, 2018, 3:10 PM
 */

#include "DatabaseHandler.h"


DatabaseHandler::DatabaseHandler() {
}

DatabaseHandler::DatabaseHandler( Database* db):db(db) {
}

DatabaseHandler::~DatabaseHandler() {
    if( db )
        delete db;
}

void DatabaseHandler::connect(){
    db->connect();
}

void DatabaseHandler::disconnect(){
    db->disconnect();
}