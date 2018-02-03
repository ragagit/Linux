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
public class BicycleAdapter implements Vehiculo{

    Bicycle bicycle;

    public BicycleAdapter(Bicycle bicycle) {
        this.bicycle = bicycle;
    }
    
    
    @Override
    public void accelerate() {
        this.bicycle.go();
    }
    
}
