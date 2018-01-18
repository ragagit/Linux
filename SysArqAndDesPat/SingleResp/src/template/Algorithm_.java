/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package template;

/**
 *
 * @author raga
 */
public abstract class Algorithm_ {
    public abstract void initialize();
    public abstract void sorting();
    public abstract void printResult();
    
    //That's why it is called template because it provides what to do
    public void sort(){
        initialize();
        sorting();
        printResult();
    }
}
