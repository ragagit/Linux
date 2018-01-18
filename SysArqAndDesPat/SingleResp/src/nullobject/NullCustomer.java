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
public class NullCustomer extends AbstractCustomer{

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public String getCustomer() {
        return "No customer with the given name in the database ...";
    }
    
}
