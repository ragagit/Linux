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
public class ElectricCar implements ElectricVehicle{

    @Override
    public void speed() {
         System.out.println("Speed up electric car ...");
    }

    @Override
    public void chargeBattery() {
        System.out.println("Charging battery ...");
    }
    
}
