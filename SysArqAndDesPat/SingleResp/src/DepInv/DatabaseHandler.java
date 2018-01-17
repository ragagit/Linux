/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DepInv;

/**
 *
 * @author raga
 */
public class DatabaseHandler {
    
    //This is tightly coupled
    //private  MySQLDatabase mySQLDatabase;
    private Database db;

    public DatabaseHandler( Database db) {
        //this.mySQLDatabase = new MySQLDatabase();
        this.db = db;
    }
    
    public void connect(){
        //this.mySQLDatabase.connect();
        db.connect();
    }
    
    public void disconnect(){
        //this.mySQLDatabase.disconnect();
        db.disconnect();
    }
    
}
