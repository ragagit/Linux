/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

/**
 *
 * @author raga
 */
public class Car implements Vehiculo{

    @Override
    public void accelerate() {
        System.out.println("Car accelerating ...");
    }
    
}
