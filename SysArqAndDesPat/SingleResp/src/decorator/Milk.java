/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decorator;

//import decorator.Beverage;
//import decorator.BeverageDecorator;

/**
 *
 * @author raga
 */
public class Milk extends BeverageDecorator{

    private Beverage beverage;

    public Milk(Beverage beverage) {
        super(beverage);
        this.beverage = beverage;
    }
    
    
    @Override
    public String getDescription() {
        
        return this.beverage.getDescription() + " milk";
    }

    @Override
    public int getCost() {
        
        return this.beverage.getCost() + 3;
    }
    
    
}
