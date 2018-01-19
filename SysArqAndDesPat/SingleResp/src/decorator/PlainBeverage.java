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
public class PlainBeverage implements Beverage{

    
    @Override
    public String getDescription() {
        return "Plain beverage";
    }

    @Override
    public int getCost() {
        return 5;
    }
    
}
