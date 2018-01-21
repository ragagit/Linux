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
public class DatabaseService implements Service{

    public static final String NAME = "Database Service";
    
    @Override
    public String getName() {
        return DatabaseService.NAME;
    }

    @Override
    public void execute() {
        System.out.println("Executing datbase service ...");
    }
    
}
