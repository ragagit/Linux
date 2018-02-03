/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicelocator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raga
 */
public class Cache {
    
    List<Service> listOfServices;

    public Cache() {
        listOfServices = new ArrayList<>();
    }
    
    public Service getService(String jndiName){
        
        for( Service s : listOfServices ){
            if( s.equals(jndiName) ){
                return s;
            }
        }   
        return null;
    }
    
    public void addService(Service service){
        listOfServices.add(service);
    }
    
    
}
