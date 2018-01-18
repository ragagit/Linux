/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nullobject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raga
 */
public class Database {
    private List<String> customerNames;

    public Database() {
        customerNames = new ArrayList<>();
        
        customerNames.add("John");
        customerNames.add("Laura");
        customerNames.add("Marco");
        customerNames.add("Alex");
        customerNames.add("Kile");
        customerNames.add("Rob");
    }
    
    public boolean existCustomer(String customer){
        for( String c : customerNames ){
            if( c.equalsIgnoreCase(customer) )
                return true;
        }
        return false;
    }
    
    
}
