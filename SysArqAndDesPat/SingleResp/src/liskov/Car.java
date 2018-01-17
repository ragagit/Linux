/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liskov;

/**
 *
 * @author raga
 */
public class Car implements NormalVehicle{

    @Override
    public void speed() {
        System.out.println("Seed up the car ...");
    }

    @Override
    public void addFuel() {
         System.out.println("Add fuel to the car ...");
    }
    
}
