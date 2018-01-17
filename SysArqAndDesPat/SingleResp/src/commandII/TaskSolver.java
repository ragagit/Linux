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
public class TaskSolver implements Command{

    private Task task;

    public TaskSolver(Task task) {
        this.task = task;
    }
    
    
    @Override
    public void execute() {
        this.task.solveProblem();
    }
    
}
