/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

/**
 *
 * @author raga
 */

//Command
public class TurnOnCommand implements Command{

    private Light light;

    public TurnOnCommand(Light light) {
        this.light = light;
    }
    
    
    @Override
    public void execute() {
        this.light.turnOn();
    }
    
}
