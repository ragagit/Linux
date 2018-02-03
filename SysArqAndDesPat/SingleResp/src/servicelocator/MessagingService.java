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
public class MessagingService implements Service{

    public static final String NAME = "Messaging Service";
    
    @Override
    public String getName() {
        return MessagingService.NAME;
    }

    @Override
    public void execute() {
        System.out.println("Executing Messaging Service");
    }
    
}
