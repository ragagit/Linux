/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandII;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author raga
 */
public class Algorithm {
    
    private BlockingQueue<Command> commandList;

    public Algorithm() {
        this.commandList = new ArrayBlockingQueue<>(10);
    }
    
    public void producer(){
        try{
            for( int i = 0; i < 10; i++ ){
            this.commandList.put(new TaskSolver( new Task(i+1)));
            }
        }catch( InterruptedException e ){
            e.getStackTrace();
        }
    }
    
    public void consumer(){
        try{
            Thread.sleep(1000);
            for( int i = 0; i < 10; i++ ){
                commandList.take().execute();
            }
        }catch( InterruptedException e){
            e.getStackTrace();
        }
    }
    
    
}
