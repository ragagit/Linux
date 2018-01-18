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
public class BubbleSort extends Algorithm_{

    private int[] numbers;

    public BubbleSort(int[] numbers) {
        this.numbers = numbers;
    }
    
    
    @Override
    public void initialize() {
        System.out.println("Initializing BubbleSort ...");
    }

    @Override
    public void sorting() {
//        for( int i=0, swap=0; i<numbers.length; i++){
//            if( numbers[i] > numbers[i+1])
//                swap = numbers[i];
//                numbers[i+1] = swap;
//                
//        }
        
    }

    @Override
    public void printResult() {
        for( int i : numbers)
            System.out.println( i );
    }
    
}
