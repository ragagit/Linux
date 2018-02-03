/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

/**
 *
 * @author raga
 */
public class SortingManager {
    
    private Algoritmo bubbleSort;
    private Algoritmo mergeSort;
    private Algoritmo quickSort;

    public SortingManager() {
        this.bubbleSort = new BubbleSort();
        this.mergeSort = new MergeSort();
        this.quickSort = new QuickSort();
    }
    
    public void doBubbleSort(){
        this.bubbleSort.sort();
    }
    
    public void doMergeSort(){
        this.mergeSort.sort();
    }
    
    public void doQuickSort(){
        this.quickSort.sort();
    }
}
