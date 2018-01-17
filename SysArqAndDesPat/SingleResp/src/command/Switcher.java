/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raga
 */

//Invoker
public class Switcher {
    
    private List<Command> commands;

    public Switcher() {
        commands = new ArrayList<>();
    }
    
    public void addCommand(Command command){
        this.commands.add(command);
    }
    
    public void executeCommand(){
        for( Command command : commands ){
            command.execute();
        }
    }
    
}
