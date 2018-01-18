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
public class CustomerFactory {
    
    private Database dataBase;

    public CustomerFactory() {
        dataBase = new Database();
    }
    
    public AbstractCustomer getCustomer(String name){
        if( checkname(name) )
            return new RealCustomer(name);
        
        return new NullCustomer();
    }
    
    public boolean checkname( String name ){
        if( dataBase.existCustomer(name))
            return true;
        
        return false;
    }
    
    
    
}
