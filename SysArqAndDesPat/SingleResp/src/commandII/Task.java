/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandII;

/**
 *
 * @author raga
 */
public class Task {
    private int id;

    public Task(int id) {
        this.id = id;
    }
    
    public void solveProblem(){
        System.out.println("Solving the problem with ID: " + id );
    }
    
}
