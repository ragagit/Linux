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
public class Bus implements Vehiculo{

    @Override
    public void accelerate() {
        System.out.println("Bus accelearting ...");
    }
    
}
