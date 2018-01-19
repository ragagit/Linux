/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decorator;

/**
 *
 * @author raga
 */
public class Sugar extends BeverageDecorator{
    
    private Beverage beverage;

    public Sugar(Beverage beverage) {
        super(beverage);
        this.beverage = beverage;
    }
    
    @Override
    public String getDescription() {
        return this.beverage.getDescription() + " sugar ";
    }

    @Override
    public int getCost() {
        return this.beverage.getCost() + 1;
    }
    
}
