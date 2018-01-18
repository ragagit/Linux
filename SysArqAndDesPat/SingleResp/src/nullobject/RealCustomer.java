/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nullobject;

/**
 *
 * @author raga
 */
public class RealCustomer extends AbstractCustomer{

    private String customerName;

    public RealCustomer(String customerName) {
        this.customerName = customerName;
    }
   
    
    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String getCustomer() {
        return this.customerName;
    }
    
}
