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
public class OracleDatabase implements Database{

    public void connect() {
        System.out.println("Connecting Oracle ...");
    }

    public void disconnect() {
        System.out.println("Disconnecting Oracle ...");
    }
     
}
