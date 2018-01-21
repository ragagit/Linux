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
public class ServiceLocator {
    
    private static Cache cache = new Cache();
    
    public static Service getService( String jndiService ){
        
        Service s = cache.getService(jndiService);
        
        if( s != null )
            return s;
        
        InitialContext ic = new InitialContext();
        s = (Service) ic.lookup(jndiService);
        cache.addService(s);
        
        return s;
        
    }
    
}
