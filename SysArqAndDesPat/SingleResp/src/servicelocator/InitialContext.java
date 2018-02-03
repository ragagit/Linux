/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicelocator;

/**
 *
 * @author raga
 */
public class InitialContext {
    
    public Object lookup( String jndiName ){
        
        switch(jndiName){
            
            case DatabaseService.NAME:
                return new DatabaseService();
            case MessagingService.NAME:
                return new MessagingService();
            default:
                return null;
        }
        
    }
    
}
