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
public class InsertionSort extends Algorithm_{

    private int[] numbers;

    public InsertionSort(int[] numbers) {
        this.numbers = numbers;
    }
    
    @Override
    public void initialize() {
        System.out.println("Initializing InsertSort ...");
    }

    @Override
    public void sorting() {
        
    }

    @Override
    public void printResult() {
        for( int i : numbers)
            System.out.println( i );        
    }
    
}
