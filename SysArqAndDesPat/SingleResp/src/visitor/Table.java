/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitor;

/**
 *
 * @author raga
 */
public class Table implements ShoppingItem{

    private String type;
    private double price;

    public Table(String type, double price) {
        this.type = type;
        this.price = price;
    }
    
    @Override
    public double accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    
}
